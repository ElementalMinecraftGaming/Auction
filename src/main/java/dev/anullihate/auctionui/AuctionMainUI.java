package dev.anullihate.auctionui;

import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowModal;
import cn.nukkit.form.window.FormWindowSimple;

public class AuctionMainUI extends FormWindowModal implements UI {

    public AuctionMainUI() {
        super("AuctionMainUI", "", "Continue", "Cancel");
        StringBuilder stringBuilder = new StringBuilder(20);
        stringBuilder.append("Auction Plugin - PROTOTYPE").append("\n");
        stringBuilder.append("For Narlea Server").append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("NON-STABLE RELEASE").append("\n");

        setContent(stringBuilder.toString());
    }

    public void onPlayerFormRespondedEvent(PlayerFormRespondedEvent event) {
        int id = getResponse().getClickedButtonId();
        if (id == 0) {
            event.getPlayer().showFormWindow(new AuctionMainMenuUI());
        } else {
            // if operation cancelled
        }
    }
}
