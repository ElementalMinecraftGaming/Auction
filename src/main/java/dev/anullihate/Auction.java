package dev.anullihate;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import dev.anullihate.auctionui.UI;
import dev.anullihate.commands.AuctionCommand;
import dev.anullihate.listeners.PlayerEventListener;

import java.io.File;

public class Auction extends PluginBase implements Listener {
    public Auction plugin;

    public Config pluginConfig;

    public static Config configAuctions;
    public static Config configNotifications;

    public void onEnable() {
        plugin = this;
        saveDefaultConfig();

        pluginConfig = getConfig();

        getServer().getCommandMap().register("auction", new AuctionCommand(this));
        getServer().getPluginManager().registerEvents(new PlayerEventListener(this), this);

        getServer().getPluginManager().registerEvents(this, this);

        saveResource("auctions.yml");
        saveResource("notifications.yml");

        configAuctions = new Config(new File(getDataFolder(), "auctions.yml"), Config.YAML);
        configNotifications = new Config(new File(getDataFolder(), "notifications.yml"), Config.YAML);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerFormRespondedEvent(PlayerFormRespondedEvent event) {
        if (event.getResponse() == null) return;
        if (!(event.getWindow() instanceof UI)) {
            return;
        }
        ((UI)event.getWindow()).onPlayerFormRespondedEvent(event);

        Auction.configAuctions.save();
    }
}
