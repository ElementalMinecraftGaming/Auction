package dev.anullihate.auctionui.sell;

import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.window.FormWindowModal;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.utils.ConfigSection;
import dev.anullihate.auctionui.AuctionItem;
import dev.anullihate.auctionui.UI;
import dev.anullihate.utils.EnchantmentUtils;

public class PlayerAuctionDetails extends FormWindowModal implements UI {

    private AuctionItem auctionItem;
    private ConfigSection playerItems;
    private String itemUUID;

    public PlayerAuctionDetails(AuctionItem auctionItem, ConfigSection playerItems, String itemUUID) {
        super(auctionItem.getName(), "", "Remove", "Go Back");
        StringBuilder stringBuilder = new StringBuilder(20);
        this.auctionItem = auctionItem;
        this.playerItems = playerItems;
        this.itemUUID = itemUUID;
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
            playerItems.remove(itemUUID);
        } else {
            event.getPlayer().showFormWindow(new AuctionSellMenu());
        }
    }
}
