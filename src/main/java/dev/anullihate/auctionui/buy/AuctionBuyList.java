package dev.anullihate.auctionui.buy;

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
import dev.anullihate.auctionui.buy.elements.BuyItemButton;
import dev.anullihate.auctionui.sell.AuctionSellDetails;
import dev.anullihate.auctionui.sell.elements.SellItemButton;

import java.util.*;

public class AuctionBuyList extends FormWindowSimple implements UI {

    public AuctionBuyList() {
        super("AuctionBuyList", "");
        ConfigSection playerSections = Auction.configAuctions.getSections();

        addButton(new ElementButton("< Back"));

        playerSections.forEach((seller, data) -> {
            ConfigSection sellerSection = Auction.configAuctions.getSection(seller);
            ConfigSection itemSections = sellerSection.getSection("items");

            itemSections.forEach((itemUUID, item) -> {
                ConfigSection itemDataSection = Auction.configAuctions.getSection(seller).getSection("items").getSection(itemUUID);
                String itemData = itemDataSection.getString("item-data");
                String buyNowPrice = itemDataSection.getString("buynow-price");
                String biddingPrice = itemDataSection.getString("bidding-price");
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
                stringBuilder.append(newItem.getName()).append(String.format(" x%s", newItem.getCount())).append("\n");
                stringBuilder.append("「Price: ")
                        .append(String.format("Buy[%s]", buyNowPrice)).append("-").append(String.format("Bid[%s]", biddingPrice))
                        .append(String.format(" Seller: %s」", seller));

                addButton(new BuyItemButton(stringBuilder.toString(), new AuctionItem(newItem), itemDataSection, sellerSection, seller, itemUUID));
            });

        });

    }

    public void onPlayerFormRespondedEvent(PlayerFormRespondedEvent event) {
        if (!(getResponse().getClickedButton() instanceof BuyItemButton)) {
            event.getPlayer().showFormWindow(new AuctionMainMenuUI());
            return;
        }

        BuyItemButton buyItemButton = (BuyItemButton)getResponse().getClickedButton();
        event.getPlayer().showFormWindow(new AuctionBuyDetails(buyItemButton.getAuctionItem(), buyItemButton.getItemDataSection(), buyItemButton.getSellerSection(), buyItemButton.getSeller(), buyItemButton.getItemUUID()));
    }
}
