package com.brpacks.nonopichy;

import com.brpacks.nonopichy.commands.MainCommand;
import com.brpacks.nonopichy.events.ProgressBar;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author https://nonopichy.github.io
 * Date: 2021/12
 */
public class TouchGold extends JavaPlugin {
    @Getter static JavaPlugin instance;
    @Getter @Setter static boolean enable = false;
    @Getter @Setter static ProgressBar progressBar;
    @SneakyThrows
    public void onEnable(){
        instance = this;
        loadYmls();
        registerCommands();
        registerEvents();
    }
    private void registerCommands(){
        new MainCommand(this);
    }
    private void registerEvents() throws Exception {
        for(String s : new String[]{"Walk", "Hand", "MultiShot", "ProgressBar"})
            Bukkit.getPluginManager().registerEvents((org.bukkit.event.Listener) Class.forName("com.brpacks.nonopichy.events."+s).newInstance(), this);
    }
    private void loadYmls(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
    public void onDisable(){
        ProgressBar.remove();
        Bukkit.getScheduler().cancelTasks(this);
        org.bukkit.event.HandlerList.unregisterAll(this);
    }
}


