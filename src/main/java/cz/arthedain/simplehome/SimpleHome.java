package cz.arthedain.simplehome;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class SimpleHome  extends JavaPlugin {
    private File homesFile;
    private FileConfiguration homesConfig;

    @Override
    public void onEnable() {
        getLogger().info("Starting SimpleHome...");
        createHomesFile();
        this.getCommand("home").setExecutor(new CommandHome(this));
        this.getCommand("sethome").setExecutor(new CommandSethome(this));
    }

    public void createHomesFile() {
        homesFile = new File(this.getDataFolder(), "homes.yml");
        if (!homesFile.exists()) {
            homesFile.getParentFile().mkdirs();
            this.saveResource("homes.yml", false);
        }
        homesConfig = YamlConfiguration.loadConfiguration(homesFile);
        try {
            homesConfig.load(homesFile);
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }

    }

    public FileConfiguration getHomesConfig() {
        return homesConfig;
    }

    public File getHomesFile() {
        return homesFile;
    }

    @Override
    public void onDisable() {
        try {
            homesConfig.save("plugins/SimpleHome/homes.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
