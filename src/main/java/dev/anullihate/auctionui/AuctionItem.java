package dev.anullihate.auctionui;

import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;

public class AuctionItem {

    private Enchantment[] enchantments;
    private int id;
    private int damage;
    private String name;
    private int itemCount;
    private int inventoryIndex;

    public AuctionItem(Item item) {
        this(item, 999);
    }

    public AuctionItem(Item item, int inventoryIndex) {
        this.enchantments = item.getEnchantments();
        this.id = item.getId();
        this.damage = item.getDamage();
        this.name = item.getName();
        this.itemCount = item.getCount();
        this.inventoryIndex = inventoryIndex;


    }

    public int getId() {
        return this.id;
    }

    public int getDamage() {
        return this.damage;
    }

    public String getName() {
        return this.name;
    }

    public int getItemCount() {
        return this.itemCount;
    }

    public Enchantment[] getEnchantments() {
        return this.enchantments;
    }

    public int getInvetoryIndex() {
        return this.inventoryIndex;
    }
}
