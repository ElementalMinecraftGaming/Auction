package dev.anullihate.listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.utils.ConfigSection;
import dev.anullihate.Auction;

import java.util.LinkedHashMap;

public class PlayerEventListener implements Listener {

    private Auction plugin;

    public PlayerEventListener(Auction plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
    }
}
