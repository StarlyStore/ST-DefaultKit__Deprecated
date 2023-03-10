package net.starly.defaultkit;

import net.starly.core.bstats.Metrics;
import net.starly.core.data.Config;
import net.starly.defaultkit.command.DefaultCmdTabComplete;
import net.starly.defaultkit.command.DefaultKitCmd;
import net.starly.defaultkit.event.InventoryCloseListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class DefaultKit extends JavaPlugin {
    private static JavaPlugin plugin;
    private final Logger log = Bukkit.getLogger();


    @Override
    public void onEnable() {
        plugin = this;
        init();
    }

    /**
     * 플러그인을 초기화 합니다.
     */
    public void init() {
        // DEPENDENCY
        if (Bukkit.getPluginManager().getPlugin("ST-Core") == null) {
            log.warning("[" + plugin.getName() + "] ST-Core 플러그인이 적용되지 않았습니다! 플러그인을 비활성화합니다.");
            log.warning("[" + plugin.getName() + "] 다운로드 링크 : &fhttps://discord.gg/TF8jqSJjCG");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        new Metrics(this, 17296);

        // CONFIG
        Config config = new Config("config", this);
        Config kit = new Config("kit", this);
        config.loadDefaultPluginConfig();
        kit.loadDefaultPluginConfig();

        // EVENT
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), this);

        // COMMAND
        Bukkit.getPluginCommand("defaultkit").setExecutor(new DefaultKitCmd());
        Bukkit.getPluginCommand("defaultkit").setTabCompleter(new DefaultCmdTabComplete());
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
