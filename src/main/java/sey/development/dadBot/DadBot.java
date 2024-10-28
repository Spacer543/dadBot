package sey.development.dadBot;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public final class DadBot extends JavaPlugin {
    public Logger logger = getLogger();
    @Override
    public void onEnable() {
        // Plugin startup logic
        File configFile = new File(getDataFolder(), "config.yml");

        // Check if the config file already exists
        if (!configFile.exists()) {
            // Save the resource if the file does not exist
            saveResource("config.yml", false);
            // Load the default configuration
            saveDefaultConfig();
        } else {
            getLogger().info("Configuration file already exists, not saving.");
        }
        getServer().getPluginManager().registerEvents(new MessageListener(this), this);
        logger.info("dadBot plugin initialized.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info("dadBot plugin terminated.");
    }
}
