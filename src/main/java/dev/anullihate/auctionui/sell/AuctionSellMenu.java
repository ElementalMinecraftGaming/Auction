package dev.anullihate.auctionui.sell;

import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import dev.anullihate.auctionui.UI;

public class AuctionSellMenu extends FormWindowSimple implements UI {

    public AuctionSellMenu() {
        super("AuctionSellMenu", "");

        addButton(new ElementButton("Sell your items"));
        addButton(new ElementButton("Check your auctions"));
    }

    @Override
    public void onPlayerFormRespondedEvent(PlayerFormRespondedEvent event) {
        int id = getResponse().getClickedButtonId();

        if (id == 0) {
            event.getPlayer().showFormWindow(new AuctionSellList(event.getPlayer()));
        } else if (id == 1) {
            event.getPlayer().showFormWindow(new PlayerAuctionList(event.getPlayer()));
        }
    }
}
