package dev.anullihate.auctionui.buy;

import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowModal;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.utils.ConfigSection;
import dev.anullihate.auctionui.AuctionItem;
import dev.anullihate.auctionui.UI;
import dev.anullihate.utils.EnchantmentUtils;
import me.onebone.economyapi.EconomyAPI;

public class AuctionBuyDetails extends FormWindowSimple implements UI {

    private AuctionItem auctionItem;
    private ConfigSection itemDataSection;
    private ConfigSection sellerSection;
    private String seller;
    private String itemUUID;

    public AuctionBuyDetails(AuctionItem auctionItem, ConfigSection itemDataSection, ConfigSection sellerSection, String seller, String itemUUID) {
        super(auctionItem.getName(), "");

        this.auctionItem = auctionItem;
        this.itemDataSection = itemDataSection;
        this.sellerSection = sellerSection;
        this.seller = seller;
        this.itemUUID = itemUUID;

        StringBuilder stringBuilder = new StringBuilder(20);
        stringBuilder.append("▶\n");
        if (auctionItem.getEnchantments().length > 0) {
            for (Enchantment enchantment : auctionItem.getEnchantments()) {
                stringBuilder.append(EnchantmentUtils.getNameById(enchantment.getId()));
                stringBuilder.append(" ").append(EnchantmentUtils.toRoman(enchantment.getLevel()));
                stringBuilder.append("\n");
            }
            stringBuilder.append("---------------------------\n");
        }
        stringBuilder.append("Item Name: ").append(auctionItem.getName()).append("\n");
        stringBuilder.append("Item Count: ").append(auctionItem.getItemCount()).append("\n");
        stringBuilder.append("---------------------------\n");
        stringBuilder.append(String.format("BuyNow Price: %s", itemDataSection.getString("buynow-price"))).append("\n");
        stringBuilder.append(String.format("BuyNow Price: %s", itemDataSection.getString("bidding-price"))).append("\n");

        setContent(stringBuilder.toString());

        addButton(new ElementButton("BUY"));
        addButton(new ElementButton("BID"));
        addButton(new ElementButton("BACK"));

    }

    @Override
    public void onPlayerFormRespondedEvent(PlayerFormRespondedEvent event) {
        int id = getResponse().getClickedButtonId();

        if (id == 0) {
            if (event.getPlayer().getName().equals(seller)) {
                event.getPlayer().sendMessage("sorry buy you can't purchase your own auction");
                return;
            }
            Item item = Item.fromString(itemDataSection.getString("item-data"));
            item.setCount(auctionItem.getItemCount());
            if (auctionItem.getEnchantments().length > 0) {
                item.addEnchantment(auctionItem.getEnchantments());
            }

            // economy operation

            double buynowPrice = Double.parseDouble(itemDataSection.getString("buynow-price"));

            EconomyAPI.getInstance().addMoney(seller, buynowPrice);
            EconomyAPI.getInstance().reduceMoney(event.getPlayer().getName(), buynowPrice);

            ConfigSection itemsSection = sellerSection.getSection("items");
            itemsSection.remove(itemUUID);

            event.getPlayer().getInventory().addItem(item);

            event.getPlayer().sendMessage(String.format("Thanks you for purchasing %s - %s ", event.getPlayer().getDisplayName(), seller));
        } else if (id == 1) {
            event.getPlayer().sendMessage("Sorry this option is not yet supported");
        } else {
            event.getPlayer().showFormWindow(new AuctionBuyList());
        }
    }
}
