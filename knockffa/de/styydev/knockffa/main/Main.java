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
 
package de.styydev.knockffa.main;

import de.styydev.knockffa.commands.SetSpawnCommand;
import de.styydev.knockffa.listener.KnockListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main plugin;

    public Main() {
    }

    public void onEnable() {
        plugin = this;
        Bukkit.getConsoleSender().sendMessage("§6KnockFFA §8| §7Plugin wurde §ageladen§7!");
        this.getCommand("setspawn").setExecutor(new SetSpawnCommand());
        PluginManager pM = Bukkit.getPluginManager();
        pM.registerEvents(new KnockListener(), this);
    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§6KnockFFA §8| §7Plugin wurde §centladen§7!");
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
