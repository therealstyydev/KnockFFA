package de.styydev.knockffa.commands;

import de.styydev.knockffa.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    public SetSpawnCommand() {
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (player.hasPermission("knockffa.setspawn")) {
                if (args.length == 0) {
                    FileConfiguration spawn = Main.getPlugin().getConfig();
                    spawn.set("Spawn.World", player.getWorld().getName());
                    spawn.set("Spawn.X", player.getLocation().getX());
                    spawn.set("Spawn.Y", player.getLocation().getY());
                    spawn.set("Spawn.Z", player.getLocation().getZ());
                    spawn.set("Spawn.Yaw", player.getLocation().getYaw());
                    spawn.set("Spawn.Pitch", player.getLocation().getPitch());
                    Main.getPlugin().saveConfig();
                    player.sendMessage("§6§lKnockFFA §8| §7Du hast den Spawnpunkt gesetzt.");
                } else {
                    player.sendMessage("§6§lKnockFFA §8| §7Falscher Befehl");
                }
            } else {
                player.sendMessage("§6§lKnockFFA §8| §7Fehlende Rechte.");
            }
        }

        return false;
    }
}
