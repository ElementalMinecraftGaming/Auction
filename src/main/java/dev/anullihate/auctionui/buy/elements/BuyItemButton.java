package dev.anullihate.auctionui.buy.elements;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.utils.ConfigSection;
import dev.anullihate.auctionui.AuctionItem;

public class BuyItemButton extends ElementButton {

    private AuctionItem auctionItem;
    private ConfigSection itemDataSection;
    private ConfigSection sellerSection;
    private String seller;
    private String itemUUID;

    public BuyItemButton(String text, AuctionItem auctionItem, ConfigSection itemDataSection, ConfigSection sellerSection, String seller, String itemUUID) {
        super(text);
        this.auctionItem = auctionItem;
        this.itemDataSection = itemDataSection;
        this.sellerSection = sellerSection;
        this.seller = seller;
        this.itemUUID = itemUUID;
    }

    public AuctionItem getAuctionItem() {
        return auctionItem;
    }

    public ConfigSection getItemDataSection() {
        return itemDataSection;
    }

    public ConfigSection getSellerSection() {
        return sellerSection;
    }

    public String getSeller() {
        return seller;
    }

    public String getItemUUID() {
        return itemUUID;
    }
}
