package cz.arthedain.simplehome;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class CommandSethome implements CommandExecutor {
    private SimpleHome main;

    public CommandSethome(SimpleHome main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location location = player.getPlayer().getLocation();
            FileConfiguration homesConfig = main.getHomesConfig();
            homesConfig.set(player.getName(), location);
            try {
                homesConfig.save("plugins/SimpleHome/homes.yml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
