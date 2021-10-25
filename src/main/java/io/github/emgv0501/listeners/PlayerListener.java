package io.github.emgv0501.listeners;


import io.github.emgv0501.commands.EternalAnvilsMainCommand;
import io.github.emgv0501.data.AnvilsLocations;
import io.github.emgv0501.data.PlayerController;
import io.github.emgv0501.utils.Utils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class PlayerListener implements Listener {

    private final AnvilsLocations anvilsLocations;
    private final JavaPlugin plugin;
    private final PlayerController playersList;


    public PlayerListener(JavaPlugin plugin, AnvilsLocations anvilsLocations, PlayerController playersList){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.anvilsLocations = anvilsLocations;
        this.playersList = playersList;

    }


    @EventHandler
    public void onPlayerPunchAnvil(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Action action = event.getAction();
        UUID uuid = player.getUniqueId();
        Location anvilLoc;

        if (playersList.checkPlayers(uuid)){

        if ((action == Action.LEFT_CLICK_BLOCK && (player.getMainHand() == null || player.getItemInHand().getType().equals(Material.AIR))))   {

            if (event.getClickedBlock().getType() == Material.ANVIL || event.getClickedBlock().getType() == Material.CHIPPED_ANVIL || event.getClickedBlock().getType() == Material.DAMAGED_ANVIL) {

               anvilLoc = event.getClickedBlock().getLocation();

                if (anvilsLocations.checkAnvil(anvilLoc) && EternalAnvilsMainCommand.actionID == 1){
                    player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&c That anvil is already an EternalAnvil."));
                    EternalAnvilsMainCommand.actionID = 0;
                    playersList.removePlayer(uuid);
                }

                if (!anvilsLocations.checkAnvil(anvilLoc) && EternalAnvilsMainCommand.actionID == 2){

                    player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&c That anvil is not an EternalAnvil."));
                    EternalAnvilsMainCommand.actionID = 0;
                    playersList.removePlayer(uuid);

                }


                if (anvilsLocations.checkAnvil(anvilLoc) && EternalAnvilsMainCommand.actionID == 2){

                    anvilsLocations.removeAnvil(anvilLoc);
                    player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&e EternalAnvil removed."));
                    EternalAnvilsMainCommand.actionID = 0;
                    playersList.removePlayer(uuid);

                }

                if (!anvilsLocations.checkAnvil(anvilLoc) && EternalAnvilsMainCommand.actionID == 1){

                    anvilsLocations.addAnvil(anvilLoc);
                    player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&e EternalAnvil set."));
                    EternalAnvilsMainCommand.actionID = 0;
                    playersList.removePlayer(uuid);

                }



            }

        }

    }


    if ((action == Action.RIGHT_CLICK_BLOCK) && (event.getClickedBlock().getType() == Material.CHIPPED_ANVIL || event.getClickedBlock().getType() == Material.DAMAGED_ANVIL)){

        anvilLoc = event.getClickedBlock().getLocation();

       if (anvilsLocations.checkAnvil(anvilLoc)){

           anvilLoc.getBlock().setType(Material.ANVIL);

       }
    }





    }
        @EventHandler(priority = EventPriority.HIGH)
        public void onAnvilBreak(BlockBreakEvent event){
            Block block = event.getBlock();
            Material blockMaterial = event.getBlock().getType();
            Location blockLocation = event.getBlock().getLocation();
            Player player = event.getPlayer();

            if (blockMaterial == Material.ANVIL || blockMaterial == Material.CHIPPED_ANVIL || blockMaterial == Material.DAMAGED_ANVIL){
                if (anvilsLocations.checkAnvil(blockLocation)){
                    event.setCancelled(true);
                    if (player.hasPermission("eternalanvils.use")) {
                        player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&c You can't break an EternalAnvil."));
                    }
                } 


            }
            }
    @EventHandler(priority = EventPriority.HIGH)
    public void onAnvilPhysicsEvent(EntityChangeBlockEvent event) {
        if (event.getBlock().getType() == Material.ANVIL && event.getEntityType() == EntityType.FALLING_BLOCK) {
            Location blockLoc = event.getBlock().getLocation();
        if (anvilsLocations.checkAnvil(blockLoc)) {
            event.setCancelled(true);
            event.getBlock().getState().update(false, false);
        }

        }
    }
    }














