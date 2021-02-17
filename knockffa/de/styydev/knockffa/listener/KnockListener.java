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

package de.styydev.knockffa.listener;

import de.styydev.knockffa.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KnockListener implements Listener {
    public KnockListener() {
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage("§6§lKnockFFA §8| §e" + player.getName() + " §7hat das Spiel betreten.");
        FileConfiguration spawn = Main.getPlugin().getConfig();
        World world = Bukkit.getWorld(spawn.getString("Spawn.World"));
        double x = spawn.getDouble("Spawn.X");
        double y = spawn.getDouble("Spawn.Y");
        double z = spawn.getDouble("Spawn.Z");
        float yaw = (float)spawn.getDouble("Spawn.Yaw");
        float pitch = (float)spawn.getDouble("Spawn.Pitch");
        Location location = new Location(world, x, y, z, yaw, pitch);
        player.teleport(location);
        player.setGameMode(GameMode.SURVIVAL);
        ItemStack is1 = new ItemStack(Material.STICK);
        ItemMeta im1 = is1.getItemMeta();
        im1.setDisplayName("§6KnockbackStick");
        im1.addEnchant(Enchantment.KNOCKBACK, 2, true);
        is1.setItemMeta(im1);
        player.getInventory().setItem(0, is1);
        ItemStack is2 = new ItemStack(Material.MAGMA_CREAM);
        ItemMeta im2 = is2.getItemMeta();
        im2.setDisplayName("§c» Lobby");
        is2.setItemMeta(im2);
        player.getInventory().setItem(8, is2);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage((String)null);
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBuild(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("knockit.build")) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onDestroy(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("knockit.build")) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        Player killer = e.getEntity().getKiller();
        e.setDeathMessage((String)null);
        FileConfiguration spawn = Main.getPlugin().getConfig();
        World world = Bukkit.getWorld(spawn.getString("Spawn.World"));
        double x = spawn.getDouble("Spawn.X");
        double y = spawn.getDouble("Spawn.Y");
        double z = spawn.getDouble("Spawn.Z");
        float yaw = (float)spawn.getDouble("Spawn.Yaw");
        float pitch = (float)spawn.getDouble("Spawn.Pitch");
        Location location = new Location(world, x, y, z, yaw, pitch);
        player.teleport(location);
        player.sendMessage("§e•§6● KnockFFA §8| §7Du bist gestorben.");
        killer.sendMessage("§e•§6● KnockFFA §8| §7Du hast §6" + player.getName() + "§7 getötet.");
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        FileConfiguration spawn = Main.getPlugin().getConfig();
        World world = Bukkit.getWorld(spawn.getString("Spawn.World"));
        double x = spawn.getDouble("Spawn.X");
        double y = spawn.getDouble("Spawn.Y");
        double z = spawn.getDouble("Spawn.Z");
        float yaw = (float)spawn.getDouble("Spawn.Yaw");
        float pitch = (float)spawn.getDouble("Spawn.Pitch");
        Location location = new Location(world, x, y, z, yaw, pitch);
        p.teleport(location);
        ItemStack is1 = new ItemStack(Material.STICK);
        ItemMeta im1 = is1.getItemMeta();
        im1.addEnchant(Enchantment.KNOCKBACK, 2, true);
        im1.setDisplayName("§6KnockbackStick");
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("knockffa.drop")) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if ((e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && e.hasItem() && e.getItem().getType() == Material.MAGMA_CREAM) {
            player.performCommand("lobby");
        }

    }
}
