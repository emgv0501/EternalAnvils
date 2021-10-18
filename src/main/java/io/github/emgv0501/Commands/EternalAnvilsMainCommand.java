package io.github.emgv0501.Commands;

import io.github.emgv0501.EternalAnvilsPlugin;
import io.github.emgv0501.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.Console;

public class EternalAnvilsMainCommand implements CommandExecutor {

    public EternalAnvilsMainCommand(EternalAnvilsPlugin plugin){
        plugin.getCommand("eternalanvils").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;

        if (!(sender instanceof Player)){
            sender.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&c The plugin command can only be executed in game."));
            return false;
        }

        if (args.length <= 0) return false;

        if (args[0].equalsIgnoreCase("set")) {
            player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&e Right click on an anvil to make it indestructible."));
        } else  if (args[0].equalsIgnoreCase("remove")){

            player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&e Right click on an anvil to make it destructible again."));

        }

        return false;
    }
}
