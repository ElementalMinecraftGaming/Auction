package dev.anullihate.auctionui.sell;

import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.window.FormWindowModal;
import cn.nukkit.item.enchantment.Enchantment;
import dev.anullihate.auctionui.AuctionItem;
import dev.anullihate.auctionui.UI;
import dev.anullihate.utils.EnchantmentUtils;

public class AuctionSellDetails extends FormWindowModal implements UI {

    private AuctionItem auctionItem;

    public AuctionSellDetails(AuctionItem auctionItem) {
        super(auctionItem.getName(), "", "Continue", "Go Back");
        StringBuilder stringBuilder = new StringBuilder(20);
        this.auctionItem = auctionItem;
        if (auctionItem.getEnchantments().length > 0) {
            for (Enchantment enchantment : auctionItem.getEnchantments()) {
                stringBuilder.append(EnchantmentUtils.getNameById(enchantment.getId()));
                stringBuilder.append(" ").append(EnchantmentUtils.toRoman(enchantment.getLevel()));
                stringBuilder.append("\n");
            }
            stringBuilder.append("----------------------------------------------\n");
        }
        stringBuilder.append("Item Count: ").append(auctionItem.getItemCount()).append("\n");
        setContent(stringBuilder.toString());
    }

    @Override
    public void onPlayerFormRespondedEvent(PlayerFormRespondedEvent event) {
        int id = getResponse().getClickedButtonId();
        if (id == 0) {
            event.getPlayer().showFormWindow(new AuctionSellInput(this.auctionItem));
        } else {
            event.getPlayer().showFormWindow(new AuctionSellList(event.getPlayer()));
        }
    }
}
