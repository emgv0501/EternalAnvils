package io.github.emgv0501.Commands;

import io.github.emgv0501.Data.AnvilsLocations;
import io.github.emgv0501.Data.PlayerController;
import io.github.emgv0501.EternalAnvilsPlugin;
import io.github.emgv0501.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.Console;
import java.util.List;
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

        if (args.length <= 0){
            player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&c Use /eternalanvils help to view plugin commands."));
            return false;
        }


        if (args[0].equalsIgnoreCase("help")){
            player.sendMessage(Utils.format("&f&l--------------- &3&lEternal&8&lAnvils&f&l ---------------"));
            player.sendMessage(Utils.format("&a/eternalanvils set: &eCreates a new EternalAnvil."));
            player.sendMessage(Utils.format("&a/eternalanvils remove: &eRemoves an existent EternalAnvil."));
            player.sendMessage(Utils.format("&a/eternalanvils removeall: &eRemoves all existent EternalAnvils."));
            player.sendMessage(Utils.format("&a/eternalanvils list: &eRetrieves a list with the location of the current EternalAnvils."));
            player.sendMessage(Utils.format("&a/eternalanvils info: &eShows general infomation about the plugin."));
        }

        if (args[0].equalsIgnoreCase("info")){

            player.sendMessage(Utils.format("&f&l--------------- &3&lEternal&8&lAnvils&f&l ---------------"));
            player.sendMessage(Utils.format("&aAuthor:&e Emgv0501"));
            player.sendMessage(Utils.format("&aVersion:&e 1.0"));



        }

        if (args[0].equalsIgnoreCase("removeall")){
            anvilsLocations.eraseList();
            player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&e Removed all EternalAnvils."));
        }

        if (args[0].equalsIgnoreCase("list")){
            player.sendMessage(Utils.format("&f&l--------------- &3&lEternal&8&lAnvils&f&l ---------------"));
            if (anvilsLocations.returnList().size() == 0){
                player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&c There aren't EternalAnvils to remove."));
                return false;
            }

            List<String> listCoords = anvilsLocations.getLocations();
            String worldFormat =   listCoords.get(0).replace("CraftWorld{name=", "");
            player.sendMessage(Utils.format("&aWorld: " + "&e" + worldFormat.replace("}", "")));
            player.sendMessage(Utils.format("&aX: " + "&e" + listCoords.get(1)));
            player.sendMessage(Utils.format("&aY: " + "&e" +  listCoords.get(2)));
            player.sendMessage(Utils.format("&aZ: " + "&e" + listCoords.get(3)));



        }

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
