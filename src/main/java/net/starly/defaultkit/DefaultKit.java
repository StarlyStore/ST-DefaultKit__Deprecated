package net.starly.defaultkit;

import net.starly.core.data.Config;
import net.starly.defaultkit.command.DefaultKitCmd;
import net.starly.defaultkit.event.InventoryCloseListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DefaultKit extends JavaPlugin {

    public static DefaultKit plugin;


    @Override
    public void onEnable() {
        plugin = this;
        init();
    }


    /**
     * 플러그인 정보를 가져옵니다.
     */
    public void init() {

        // CONFIG
        Config config = new Config("config", this);
        config.loadDefaultPluginConfig();

        // EVENT
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), this);

        // COMMAND
        Bukkit.getPluginCommand("defaultkit").setExecutor(new DefaultKitCmd());
    }
}
