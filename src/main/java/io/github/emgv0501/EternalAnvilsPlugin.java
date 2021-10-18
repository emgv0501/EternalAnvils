package io.github.emgv0501;

import io.github.emgv0501.Commands.EternalAnvilsMainCommand;
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
        new EternalAnvilsMainCommand(this);
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







