package dev.anullihate.auctionui.sell;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.utils.ConfigSection;
import dev.anullihate.Auction;
import dev.anullihate.auctionui.AuctionItem;
import dev.anullihate.auctionui.AuctionMainMenuUI;
import dev.anullihate.auctionui.UI;
import dev.anullihate.auctionui.sell.elements.PlayerItemButton;

import java.util.ArrayList;

public class PlayerAuctionList extends FormWindowSimple implements UI {

    public PlayerAuctionList(Player player) {
        super(String.format("%s's Auctions", player.getDisplayName()), "");

        String playerName = player.getDisplayName();

        ConfigSection playerAuction = Auction.configAuctions.getSection(player.getDisplayName());
        ConfigSection playerItems = playerAuction.getSection("items");

        addButton(new ElementButton("< Back"));

        playerItems.forEach((itemUUID, item) -> {
            ConfigSection itemDataSection = Auction.configAuctions.getSection(playerName).getSection("items").getSection(itemUUID);
            String itemData = itemDataSection.getString("item-data");
            String buyNowPrice = itemDataSection.getString("buynow-price");
            String biddingPrice = itemDataSection.getString("bidding-price");
            String[] itemDataArray = itemData.split(":");
            int itemCount = itemDataSection.getInt("item-count");

            //enchantments
            ArrayList<Enchantment> enchantments = new ArrayList<>();
            if (!itemDataSection.getStringList("enchantments-data").isEmpty()) {
                for (String enchantDataString : itemDataSection.getStringList("enchantments-data")) {
                    String[] enchantDataSplit = enchantDataString.split(":");
                    Enchantment enchantment = Enchantment.getEnchantment(Integer.parseInt(enchantDataSplit[0]));
                    enchantment.setLevel(Integer.parseInt(enchantDataSplit[1]));

                    enchantments.add(enchantment);
                }
            }

            Item newItem = Item.fromString(itemData);
            newItem.setCount(itemCount);

            // set enchantments if found
            if (!enchantments.isEmpty()) {
                for (Enchantment enchantment : enchantments) {
                    newItem.addEnchantment(enchantment);
                }
            }

            StringBuilder stringBuilder = new StringBuilder(20);
            stringBuilder.append(newItem.getName()).append(" x").append(newItem.getCount()).append("\n");

            addButton(new PlayerItemButton(stringBuilder.toString(), new AuctionItem(newItem), playerItems, itemUUID));
        });

    }

    @Override
    public void onPlayerFormRespondedEvent(PlayerFormRespondedEvent event) {
        if (!(getResponse().getClickedButton() instanceof PlayerItemButton)) {
            event.getPlayer().showFormWindow(new AuctionSellMenu());
            return;
        }

        PlayerItemButton playerItemButton = (PlayerItemButton)getResponse().getClickedButton();
        event.getPlayer().showFormWindow(new PlayerAuctionDetails(playerItemButton.getAuctionItem(), playerItemButton.getPlayerItems(), playerItemButton.getItemUUID()));
    }
}
