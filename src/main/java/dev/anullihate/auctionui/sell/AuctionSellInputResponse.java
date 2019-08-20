package dev.anullihate.auctionui.sell;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowModal;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.enchantment.Enchantment;
import dev.anullihate.auctionui.AuctionItem;
import dev.anullihate.auctionui.UI;
import dev.anullihate.utils.EnchantmentUtils;

import java.util.UUID;

public class AuctionSellInputResponse extends FormWindowModal implements UI {

    public AuctionSellInputResponse(AuctionItem auctionItem, Player player, UUID itemUUID, int selection) {
        super(String.format("%s has been sold", auctionItem.getName()), "", "DONE", "SOON");
        StringBuilder stringBuilder = new StringBuilder(20);
        stringBuilder.append("Successfully added!").append("\n");
        stringBuilder.append("----------------------------------------------\n");
        if (auctionItem.getEnchantments().length > 0) {
            for (Enchantment enchantment : auctionItem.getEnchantments()) {
                stringBuilder.append(EnchantmentUtils.getNameById(enchantment.getId()));
                stringBuilder.append(" ").append(EnchantmentUtils.toRoman(enchantment.getLevel()));
                stringBuilder.append("\n");
            }
            stringBuilder.append("----------------------------------------------\n");
        }
        stringBuilder.append("Seller: ").append(player.getDisplayName()).append("\n");
        stringBuilder.append("UUID: ").append(player.getUniqueId().toString()).append("\n");
        stringBuilder.append("Item Data: ").append(auctionItem.getId()).append(":").append(auctionItem.getDamage()).append("\n");
        stringBuilder.append("Item UUID").append(itemUUID.toString()).append("\n");
        stringBuilder.append("Item Count: ").append(selection).append("\n");
        setContent(stringBuilder.toString());
    }

    @Override
    public void onPlayerFormRespondedEvent(PlayerFormRespondedEvent event) {
        //
    }
}
