package io.github.emgv0501.Data;


import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AnvilsLocations {

    private final List<Location> anvilLocation;


    public AnvilsLocations() {
        anvilLocation = new ArrayList<>();
    }


    public void removeAnvil(Location location){anvilLocation.remove(location);}
    public void addAnvil(Location location) {anvilLocation.add(location);}


    public boolean checkAnvil(Location location){
        if (anvilLocation.contains(location)){
            return true;
        } else return false;
    }
}
