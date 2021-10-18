package io.github.emgv0501.Commands;

import io.github.emgv0501.Data.AnvilsLocations;
import io.github.emgv0501.Data.PlayerController;
import io.github.emgv0501.EternalAnvilsPlugin;
import io.github.emgv0501.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.Console;
import java.util.Objects;
import java.util.UUID;

public class EternalAnvilsMainCommand implements CommandExecutor {
    public static int actionID = 0;
    private  final AnvilsLocations anvilsLocations;
    private  final PlayerController playersList;


    public EternalAnvilsMainCommand(EternalAnvilsPlugin plugin, PlayerController playersList, AnvilsLocations anvilsLocations) {
        this.anvilsLocations = anvilsLocations;
        this.playersList = playersList;
        plugin.getCommand("eternalanvils").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        if (!(sender instanceof Player)){
            sender.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&c The plugin command can only be executed in game."));
            return false;
        }

        if (args.length <= 0) return false;



        if (args[0].equalsIgnoreCase("set")) {
            playersList.addPlayer(uuid);
            player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&e Left click on an anvil to make it indestructible."));
            actionID = 1;
        } else if (args[0].equalsIgnoreCase("remove")){
            playersList.addPlayer(uuid);
            player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&e Left click on an anvil to make it destructible again."));
            actionID = 2;

        }
        return false;
    }
}
