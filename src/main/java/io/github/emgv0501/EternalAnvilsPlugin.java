package io.github.emgv0501;

import io.github.emgv0501.Commands.EternalAnvilsMainCommand;
import io.github.emgv0501.Data.AnvilsLocations;
import io.github.emgv0501.Data.PlayerController;
import io.github.emgv0501.Listeners.PlayerListener;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class EternalAnvilsPlugin extends  JavaPlugin{

    private File anvilDBFile;
    private FileConfiguration anvilDBConfig;
    private AnvilsLocations anvilsLocations;



    @Override
    public void onEnable() {
        anvilsLocations = new AnvilsLocations();
        PlayerController playersList = new PlayerController();
        new PlayerListener(this, anvilsLocations, playersList);
        new EternalAnvilsMainCommand(this, playersList, anvilsLocations);
        createCustomDB();
        System.out.println(anvilsLocations.returnList());
        System.out.println("Enabling EternalAnvils.");
    }


    @Override
    public void onDisable() {
        System.out.println("Disabling EternalAnvils. Goodbye :).");
        //String listString = (anvilsLocations.returnList()).stream().map(Object::toString).collect(Collectors.joining(", "));
        getCustomDB().set("anvil-locations", anvilsLocations.returnList());
        try {
            getCustomDB().save(anvilDBFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        List prueba0 = getCustomDB().getList("anvil-locations");
        List prueba = anvilsLocations.returnList();
        String prueba2 = anvilsLocations.returnList().toString();
        System.out.println(prueba0);
        System.out.println(prueba);
        System.out.println(prueba2);
        System.out.println(anvilsLocations.returnList());
        System.out.println(getCustomDB().getList("anvil-locations"));

    }



    public FileConfiguration getCustomDB() {
        return this.anvilDBConfig;
    }


    private void createCustomDB() {
        anvilDBFile = new File(getDataFolder(), "anvildb.yml");
        if (!anvilDBFile.exists()) {
            anvilDBFile.getParentFile().mkdirs();
            saveResource("anvildb.yml", false);
        }

        anvilDBConfig= new YamlConfiguration();
        try {
            anvilDBConfig.load(anvilDBFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}







