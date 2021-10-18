package io.github.emgv0501;

import io.github.emgv0501.Commands.EternalAnvilsMainCommand;
import io.github.emgv0501.Data.AnvilsLocations;
import io.github.emgv0501.Data.PlayerController;
import io.github.emgv0501.Listeners.PlayerListener;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;


public class EternalAnvilsPlugin extends  JavaPlugin{

    private File anvilDBFile;
    private FileConfiguration anvilDBConfig;


    @Override
    public void onEnable() {
        AnvilsLocations anvilsLocations = new AnvilsLocations();
        PlayerController playersList = new PlayerController();
        new PlayerListener(this, anvilsLocations, playersList);
        new EternalAnvilsMainCommand(this, playersList, anvilsLocations);
        createCustomDB();
        System.out.println("Enabling EternalAnvils.");
    }


    @Override
    public void onDisable() {
        System.out.println("Disabling EternalAnvils. Goodbye :).");

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







