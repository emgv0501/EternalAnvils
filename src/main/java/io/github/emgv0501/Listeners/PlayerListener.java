package io.github.emgv0501.Listeners;


import io.github.emgv0501.Commands.EternalAnvilsMainCommand;
import io.github.emgv0501.Data.AnvilsLocations;
import io.github.emgv0501.Data.PlayerController;
import io.github.emgv0501.Utils.Utils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
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

        if (playersList.checkPlayers(uuid)){ // && player.hasPermission("eternalanvils.use")){

        if ((action == Action.LEFT_CLICK_BLOCK && (player.getMainHand() == null || player.getItemInHand().getType().equals(Material.AIR))))   {

            if (event.getClickedBlock().getType() == Material.ANVIL || event.getClickedBlock().getType() == Material.CHIPPED_ANVIL || event.getClickedBlock().getType() == Material.DAMAGED_ANVIL) {

               anvilLoc = event.getClickedBlock().getLocation();



                if (anvilsLocations.checkAnvil(anvilLoc) && EternalAnvilsMainCommand.actionID == 2){

                    anvilsLocations.removeAnvil(anvilLoc);
                    player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&e Eternal Anvil removed."));
                    EternalAnvilsMainCommand.actionID = 0;
                    playersList.removePlayer(uuid);

                }

                if (!anvilsLocations.checkAnvil(anvilLoc) && EternalAnvilsMainCommand.actionID == 1){

                    anvilsLocations.addAnvil(anvilLoc);
                    player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&e Eternal Anvil set."));
                    EternalAnvilsMainCommand.actionID = 0;
                    playersList.removePlayer(uuid);

                }



            }

        }

    }
    }
        @EventHandler
        public void onAnvilBreak(BlockBreakEvent event){
            Block block = event.getBlock();
            Material blockMaterial = event.getBlock().getType();
            Location blockLocation = event.getBlock().getLocation();
            Player player = event.getPlayer();

            if (blockMaterial == Material.ANVIL || blockMaterial == Material.CHIPPED_ANVIL || blockMaterial == Material.DAMAGED_ANVIL){

                if (anvilsLocations.checkAnvil(blockLocation)){
                    event.setCancelled(true);
                } 


            }



            }

}














