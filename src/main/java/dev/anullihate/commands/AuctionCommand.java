package dev.anullihate.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import dev.anullihate.Auction;
import dev.anullihate.auctionui.AuctionMainUI;

public class AuctionCommand extends Command {

    private Auction plugin;

    public AuctionCommand(Auction plugin) {
        super("auction", "open auction ui", "/auction", new String[]{"a"});
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {

        Player player = this.plugin.getServer().getPlayer(sender.getName());

        player.showFormWindow(new AuctionMainUI());
        return true;
    }
}
