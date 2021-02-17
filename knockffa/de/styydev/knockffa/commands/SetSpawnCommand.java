 /* Copyright (c) 2021 StyyDev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
