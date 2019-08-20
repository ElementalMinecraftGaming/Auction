package dev.anullihate.auctionui.sell.elements;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.item.enchantment.Enchantment;
import dev.anullihate.auctionui.AuctionItem;

public class SellItemButton extends ElementButton {

    private AuctionItem auctionItem;

    public SellItemButton(String text, AuctionItem auctionItem) {
        super(text);

        this.auctionItem = auctionItem;
    }

    public AuctionItem getAuctionItem() {
        return auctionItem;
    }
}
