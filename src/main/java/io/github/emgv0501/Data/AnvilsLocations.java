package io.github.emgv0501.Data;


import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AnvilsLocations {

    private final List<Location> anvilLocation;


    public AnvilsLocations() {
        anvilLocation = new ArrayList<>();
    }


    public void removeAnvil(Location location){anvilLocation.remove(location);}
    public void addAnvil(Location location) {anvilLocation.add(location);}
    public void addList(List<Location> locations){anvilLocation.addAll(locations);}
    public  void eraseList(){anvilLocation.clear();}
    public List<Location>  returnList(){return anvilLocation;}



    public boolean checkAnvil(Location location){
        if (anvilLocation.contains(location)){
            return true;
        } else return false;
    }
}


