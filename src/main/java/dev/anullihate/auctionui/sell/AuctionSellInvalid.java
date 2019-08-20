package dev.anullihate.auctionui.sell;

import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.window.FormWindowModal;
import dev.anullihate.auctionui.UI;
import dev.anullihate.auctionui.sell.AuctionSellList;

public class AuctionSellInvalid extends FormWindowModal implements UI {

    public AuctionSellInvalid() {
        super("AuctionSellInvalid", "ERROR", "Back", "Cancel");
    }

    @Override
    public void onPlayerFormRespondedEvent(PlayerFormRespondedEvent event) {
        int id = getResponse().getClickedButtonId();
        if (id == 0) {
            event.getPlayer().showFormWindow(new AuctionSellList(event.getPlayer()));
        }
    }
}
