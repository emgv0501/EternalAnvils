package io.github.emgv0501.Listeners;


import io.github.emgv0501.Data.AnvilsLocations;
import io.github.emgv0501.Data.PlayerController;
import io.github.emgv0501.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
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

        if (playersList.checkPlayers(uuid)){

        if ((action == Action.LEFT_CLICK_BLOCK && (player.getMainHand() == null || player.getItemInHand().getType().equals(Material.AIR))))   {

            if (event.getClickedBlock().getType().equals(Material.ANVIL)) {

               anvilLoc = event.getClickedBlock().getLocation();

                if (anvilsLocations.checkAnvil(anvilLoc)){

                    anvilsLocations.removeAnvil(anvilLoc);
                    player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&e Eternal Anvil removed."));


                } else {

                    anvilsLocations.addAnvil(anvilLoc);
                    player.sendMessage(Utils.format("&f&l[&3&lEternal&8&lAnvils&f&l]&e Eternal Anvil set."));

                }
                playersList.removePlayer(uuid);


            }

        }

    }
}
}









