package io.github.emgv0501;

import io.github.emgv0501.Commands.EternalAnvilsMainCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class EternalAnvilsPlugin extends  JavaPlugin{


    @Override
    public void onEnable() {
        new EternalAnvilsMainCommand(this);
        System.out.println("Enabling EternalAnvils.");
    }



    @Override
    public void onDisable() {
        System.out.println("Disabling EternalAnvils. Goodbye :).");
    }


}


