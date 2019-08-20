package dev.anullihate.auctionui.sell;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import dev.anullihate.auctionui.AuctionMainMenuUI;
import dev.anullihate.auctionui.AuctionItem;
import dev.anullihate.auctionui.UI;
import dev.anullihate.auctionui.sell.elements.SellItemButton;

import java.util.LinkedHashMap;

public class AuctionSellList extends FormWindowSimple implements UI {

    public AuctionSellList(Player player) {
        super("AuctionSellList", "");

        addButton(new ElementButton("< Back"));


        player.getInventory().getContents().forEach((key, value) -> {
            int InventoryIndex = key;
            Item itemObject = value;

            String buttonText = String.format("%s x%d", itemObject.getName(), itemObject.getCount());
            if (itemObject.hasEnchantments()) {
                addButton(new SellItemButton(buttonText, new AuctionItem(itemObject, InventoryIndex)));
            } else {
                addButton(new SellItemButton(buttonText, new AuctionItem(itemObject, InventoryIndex)));
            }
        });

    }

    public void onPlayerFormRespondedEvent(PlayerFormRespondedEvent event) {
        if (!(getResponse().getClickedButton() instanceof SellItemButton)) {
            event.getPlayer().showFormWindow(new AuctionMainMenuUI());
            return;
        }

        SellItemButton sellItemButton = (SellItemButton)getResponse().getClickedButton();
        event.getPlayer().showFormWindow(new AuctionSellDetails(sellItemButton.getAuctionItem()));
    }
}
