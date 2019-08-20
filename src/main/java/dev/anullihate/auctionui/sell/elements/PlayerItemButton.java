package dev.anullihate.auctionui.sell.elements;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.utils.ConfigSection;
import dev.anullihate.auctionui.AuctionItem;

public class PlayerItemButton extends ElementButton {

    private AuctionItem auctionItem;
    private ConfigSection playerItems;
    private String itemUUID;

    public PlayerItemButton(String text, AuctionItem auctionItem, ConfigSection playerItems, String itemUUID) {
        super(text);

        this.auctionItem = auctionItem;
        this.playerItems = playerItems;
        this.itemUUID = itemUUID;
    }

    public AuctionItem getAuctionItem() {
        return auctionItem;
    }

    public String getItemUUID() {
        return itemUUID;
    }

    public ConfigSection getPlayerItems() {
        return playerItems;
    }
}
