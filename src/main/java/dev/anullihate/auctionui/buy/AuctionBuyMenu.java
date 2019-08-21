package dev.anullihate.auctionui.buy;

import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import cn.nukkit.utils.ConfigSection;
import dev.anullihate.Auction;
import dev.anullihate.auctionui.AuctionItem;
import dev.anullihate.auctionui.UI;
import dev.anullihate.auctionui.buy.elements.BuyItemButton;

public class AuctionBuyMenu extends FormWindowSimple implements UI {

    public AuctionBuyMenu() {
        super("AuctionBuyMenu", "");

        addButton(new ElementButton("Check Marketplace"));
        addButton(new ElementButton("Your Bids"));
    }

    @Override
    public void onPlayerFormRespondedEvent(PlayerFormRespondedEvent event) {
        int id = getResponse().getClickedButtonId();

        if (id == 0) {
            event.getPlayer().showFormWindow(new AuctionBuyList());
        } else if (id == 1) {
            event.getPlayer().sendMessage("Sorry this option is not yet supported");
        }
    }
}
