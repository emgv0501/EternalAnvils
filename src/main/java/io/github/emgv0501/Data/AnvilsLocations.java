package io.github.emgv0501.Data;


import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

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

    public boolean checkAnvil(Location location){
        if (anvilLocation.contains(location)){
            return true;
        } else return false;
    }
}


