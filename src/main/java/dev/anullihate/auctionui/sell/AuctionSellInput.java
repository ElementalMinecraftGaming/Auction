package dev.anullihate.auctionui.sell;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementSlider;
import cn.nukkit.form.element.ElementToggle;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import dev.anullihate.Auction;
import dev.anullihate.auctionui.AuctionItem;
import dev.anullihate.auctionui.UI;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.UUID;

public class AuctionSellInput extends FormWindowCustom implements UI {

    private AuctionItem auctionItem;

    public AuctionSellInput(AuctionItem auctionItem) {
        super("AuctionSellInput");

        this.auctionItem = auctionItem;

        addElement(new ElementInput("BuyNow Price", "", "50"));
        addElement(new ElementInput("Bidding Price", "", "25"));

        if (auctionItem.getItemCount() > 1) {
            addElement(new ElementSlider("Selection", 1, auctionItem.getItemCount(), 1, 1));
        }
    }

    public void onPlayerFormRespondedEvent(PlayerFormRespondedEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getDisplayName();

        UUID itemUUID = UUID.randomUUID();

        try {
            String buyNowPrice = getResponse().getInputResponse(0);
            String biddingPrice = getResponse().getInputResponse(1);
            int selection = auctionItem.getItemCount() > 1 ? (int)getResponse().getSliderResponse(2) : 1;

            if (buyNowPrice.isEmpty() || biddingPrice.isEmpty()) {
                event.getPlayer().showFormWindow(new AuctionSellInvalid());
                return;
            }
            if (Integer.parseInt(buyNowPrice) <= Integer.parseInt(biddingPrice)) {
                event.getPlayer().showFormWindow(new AuctionSellInvalid());
                return;
            }

            LinkedHashMap<String, Object> map = new LinkedHashMap<>();

            LinkedHashMap<String, Object> itemMap = new LinkedHashMap<String, Object>() {
                {
                    put("item-name", auctionItem.getName());
                    put("item-data", String.format("%s:%s", auctionItem.getId(), auctionItem.getDamage()));
                    put("item-count", selection);
                    put("enchantments-data", new ArrayList<String>() {
                        {
                            if (auctionItem.getEnchantments().length > 0) {
                                for (Enchantment enchantment : auctionItem.getEnchantments()) {
                                    add(String.format("%s:%s", enchantment.getId(), enchantment.getLevel()));
                                }
                            }
                        }
                    });
                    put("buynow-price", buyNowPrice);
                    put("bidding-price", biddingPrice);
                    put("expiration", 0);
                    put("bidders", new ConfigSection());
                }
            };

            if (Auction.configAuctions.getSection(playerName).isEmpty()) {
                // new auctioneer data
                map.put("uuid", player.getUniqueId().toString());
                map.put("max-slot", 20);
                map.put("items", new ConfigSection(new LinkedHashMap<String, Object>() {
                    {
                        put(itemUUID.toString(), new ConfigSection(itemMap));
                    }
                }));
                ConfigSection playerSection = new ConfigSection(map);
                Auction.configAuctions.set(playerName, playerSection);
            } else {
                // existing auctioneer data
                ConfigSection playerSection = Auction.configAuctions.getSection(playerName);
                if (playerSection.getSection("items").size() >= playerSection.getInt("max-slot")) {
                    player.sendMessage("you can't add more items in auction you already reach the limit");
                    return;
                }

                playerSection.getSection("items").set(itemUUID.toString(), new ConfigSection(itemMap));
            }

            Item item = player.getInventory().getItem(auctionItem.getInvetoryIndex());
            item.setCount(selection);

            player.getInventory().removeItem(item);

            event.getPlayer().showFormWindow(new AuctionSellInputResponse(auctionItem, player, itemUUID, selection));

            Auction.configAuctions.save();
        } catch (Exception e) {
            event.getPlayer().showFormWindow(new AuctionSellInvalid());
            e.printStackTrace();
        }
    }
}
