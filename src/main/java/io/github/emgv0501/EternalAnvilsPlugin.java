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
    public String ver = getDescription().getVersion();



    @Override
    public void onEnable() {
        anvilsLocations = new AnvilsLocations();
        PlayerController playersList = new PlayerController();
        new PlayerListener(this, anvilsLocations, playersList);
        new EternalAnvilsMainCommand(this, playersList, anvilsLocations);
        createCustomDB();
        List<Location> locations = (List<Location>)getCustomDB().getList("anvil-locations");
        anvilsLocations.addList(locations);
        System.out.println("EternalAnvils enabled.");
    }


    @Override
    public void onDisable() {
        System.out.println("Disabling EternalAnvils. Goodbye :).");
        getCustomDB().set("anvil-locations", anvilsLocations.returnList());
        try {
            getCustomDB().save(anvilDBFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public FileConfiguration getCustomDB() {
        return this.anvilDBConfig;
    }
    public String getVer(String ver){return  ver;}


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







