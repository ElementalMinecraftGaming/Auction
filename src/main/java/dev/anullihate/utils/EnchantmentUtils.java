package dev.anullihate.utils;

import java.util.TreeMap;

public class EnchantmentUtils {

    public static String getNameById(int id) {
        switch (id) {
            case 0:
                return "Protection";
            case 1:
                return "Fire Protection";
            case 2:
                return "Feather Falling";
            case 3:
                return "Blast Protection";
            case 4:
                return "Projectile Projection";
            case 5:
                return "Thorns";
            case 6:
                return "Respiration";
            case 7:
                return "Aqua Affinity";
            case 8:
                return "Depth Strider";
            case 9:
                return "Sharpness";
            case 10:
                return "Smite";
            case 11:
                return "Bane of Arthropods";
            case 12:
                return "Knockback";
            case 13:
                return "Fire Aspect";
            case 14:
                return "Looting";
            case 15:
                return "Efficiency";
            case 16:
                return "Silk Touch";
            case 17:
                return "Durability";
            case 18:
                return "Fortune";
            case 19:
                return "Power";
            case 20:
                return "Punch";
            case 21:
                return "Flame";
            case 22:
                return "Infinity";
            case 23:
                return "Luck of the Sea";
            case 24:
                return "Lure";
            case 25:
                return "Frost Walker";
            case 26:
                return "Mending";
            case 27:
                return "Binding Curse";
            case 28:
                return "Vanishing Curse";
            case 29:
                return "Impaling";
            case 30:
                return "Loyality";
            case 31:
                return "Riptide";
            case 32:
                return "Channeling";
            default:
                return "Unknown";
        }
    }

    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    static {

        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

    }

    public final static String toRoman(int number) {
        int l =  map.floorKey(number);
        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number-l);
    }
}
