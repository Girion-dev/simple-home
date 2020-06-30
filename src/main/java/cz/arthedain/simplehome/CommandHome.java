package cz.arthedain.simplehome;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class CommandHome implements CommandExecutor {
    private SimpleHome main;

    public CommandHome(SimpleHome main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            FileConfiguration homesConfig = main.getHomesConfig();
            if (!homesConfig.contains(player.getUniqueId().toString())) {
                player.sendMessage("You don't have a home!");
                return false;
            } else {
                Location home = (Location) homesConfig.get(player.getUniqueId().toString());
                player.teleport(home);
            }
        }
        return true;
    }
}
