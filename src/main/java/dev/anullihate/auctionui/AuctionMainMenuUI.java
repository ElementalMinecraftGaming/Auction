package dev.anullihate.auctionui;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import dev.anullihate.auctionui.buy.AuctionBuyList;
import dev.anullihate.auctionui.buy.AuctionBuyMenu;
import dev.anullihate.auctionui.sell.AuctionSellList;
import dev.anullihate.auctionui.sell.AuctionSellMenu;

public class AuctionMainMenuUI extends FormWindowSimple implements UI {
    public AuctionMainMenuUI() {
        super("AuctionMainMenuUI", "");
        addButton(new ElementButton("< Back"));
        addButton(new ElementButton("You have 0 notifications"));
        addButton(new ElementButton("Buy"));
        addButton(new ElementButton("Sell"));
    }

    public void onPlayerFormRespondedEvent(PlayerFormRespondedEvent event) {
        int id = getResponse().getClickedButtonId();
        Player player = event.getPlayer();
        if (id == 0) {
            event.getPlayer().showFormWindow(new AuctionMainUI());
        } else if (id == 1) {
            // check notification
            event.getPlayer().sendMessage("sorry but notification is not yet support");
        } else if (id == 2) {
            // go to buy section
            event.getPlayer().showFormWindow(new AuctionBuyMenu());
        } else if (id == 3) {
            // go to sell section
            event.getPlayer().showFormWindow(new AuctionSellMenu());
        }
    }
}
