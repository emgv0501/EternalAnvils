package io.github.emgv0501.Commands;

import io.github.emgv0501.Data.AnvilsLocations;
import io.github.emgv0501.Data.PlayerController;
import io.github.emgv0501.EternalAnvilsPlugin;
import io.github.emgv0501.Utils.Utils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

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

        if (args.length <= 0 && player.hasPermission("eternalanvils.use")){
            player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&c Use /eternalanvils help to view plugin commands."));
            return false;
        } else if (args.length <= 0) {
            player.sendMessage(Utils.format("&cYou don't have permission to use this command."));
            return false;
        }


        if (args[0].equalsIgnoreCase("help") && player.hasPermission("eternalanvils.use")){
            player.sendMessage(Utils.format("&f&l--------------- &3&lEternal&8&lAnvils&f&l ---------------"));
            player.sendMessage(Utils.format("&a/eternalanvils set: &eCreates a new EternalAnvil."));
            player.sendMessage(Utils.format("&a/eternalanvils remove: &eRemoves an existent EternalAnvil."));
            player.sendMessage(Utils.format("&a/eternalanvils removeall: &eRemoves all existent EternalAnvils."));
            player.sendMessage(Utils.format("&a/eternalanvils list: &eRetrieves a list with the location of the current EternalAnvils."));
            player.sendMessage(Utils.format("&a/eternalanvils info: &eShows general infomation about the plugin."));
        }

        if (args[0].equalsIgnoreCase("info") && player.hasPermission("eternalanvils.use")){

            player.sendMessage(Utils.format("&f&l--------------- &3&lEternal&8&lAnvils&f&l ---------------"));
            player.sendMessage(Utils.format("&aAuthor:&e Emgv0501"));
            player.sendMessage(Utils.format("&aVersion:&e 1.0"));



        }

        if (args[0].equalsIgnoreCase("removeall") && player.hasPermission("eternalanvils.use")){
            if (anvilsLocations.returnList().size() == 0){
                player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&c There aren't any EternalAnvils to remove."));
                return false;
            }
            anvilsLocations.eraseList();
            player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&e Removed all EternalAnvils."));

        }

        if (args[0].equalsIgnoreCase("list") && player.hasPermission("eternalanvils.use")){


            if (anvilsLocations.returnList().size() == 0){
                player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&c There aren't any EternalAnvils."));
                return false;
            } else {

                List<Location> listCoords = anvilsLocations.returnList();
                AtomicInteger listCount = new AtomicInteger();
                player.sendMessage(Utils.format("&f&l--------------- &3&lEternal&8&lAnvils&f&l ---------------"));
                player.sendMessage(Utils.format("&a&lAnvils list:"));
                listCoords.stream().forEach(location -> {
                    String worldFormat = location.getWorld().toString().replace("CraftWorld{name=", "");
                    listCount.getAndIncrement();
                    TextComponent tp = new TextComponent("[TP]");
                    tp.setColor(ChatColor.DARK_GREEN);
                    tp.setBold(true);
                    tp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder ("Click to teleport to the EternalAnvil.").create()));
                    tp.setClickEvent( new ClickEvent( ClickEvent.Action.SUGGEST_COMMAND, "/tp " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ() + " 0" + " 0 " + worldFormat.replace("}", "") ) );
                    player.sendMessage(Utils.format("&e"+ String.valueOf(listCount.get()) + ".-" + " &aWorld: " + "&e" + worldFormat.replace("}", "") + " &aX: " + "&e" + location.getBlockX() + " &aY: " + "&e" + location.getBlockY() + " &aZ: " + "&e" + location.getBlockZ()));
                    player.spigot().sendMessage(tp);

                });

            }
        }

        if (args[0].equalsIgnoreCase("set") && player.hasPermission("eternalanvils.use")) {
            playersList.addPlayer(uuid);
            player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&e Left click on an anvil to make it indestructible."));
            actionID = 1;
        } else if (args[0].equalsIgnoreCase("remove") && player.hasPermission("eternalanvils.use")){
            playersList.addPlayer(uuid);
            player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&e Left click on an anvil to make it destructible again."));
            actionID = 2;

        }
        return false;
    }
}
