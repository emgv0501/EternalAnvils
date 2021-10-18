package io.github.emgv0501.Listeners;

import jdk.jfr.internal.PlatformEventType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class PlayerListener implements Listener {


    @EventHandler
    public void onPlayerPunchAnvil(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Action action = event.getAction();
        Location anvilLoc = event.getClickedBlock().getLocation();

        if (action == Action.LEFT_CLICK_BLOCK && (player.getMainHand() == null || player.getItemInHand().getType().equals(Material.AIR)))   {

            if (Objects.requireNonNull(event.getClickedBlock()).getType().equals(Material.ANVIL)){





            }

        }











}
}
