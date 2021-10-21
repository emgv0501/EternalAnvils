package io.github.emgv0501.Data;


import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AnvilsLocations {

    private final List<Location> anvilLocation;
    private final List<String> anvilLocationString;



    public AnvilsLocations() {
        anvilLocation = new ArrayList<>();
        anvilLocationString = new  ArrayList<>();
    }


    public void removeAnvil(Location location){anvilLocation.remove(location);}
    public void addAnvil(Location location) {anvilLocation.add(location);}
    public void addList(List<Location> locations){anvilLocation.addAll(locations);}
    public  void eraseList(){anvilLocation.clear();}
    public List<Location>  returnList(){return anvilLocation;}
    public List<String> getLocations(){
        String world = "";
        String x = "";
        String y= "";
        String z= "";
        for (Location location : anvilLocation){
           world = location.getWorld().toString();
            x = String.valueOf(location.getBlockX());
             y = String.valueOf(location.getBlockY());
           z = String.valueOf(location.getBlockZ());
            anvilLocationString.add(world);
            anvilLocationString.add(x);
            anvilLocationString.add(y);
            anvilLocationString.add(z);
        }

        return anvilLocationString;
    }


    public boolean checkAnvil(Location location){
        if (anvilLocation.contains(location)){
            return true;
        } else return false;
    }
}


