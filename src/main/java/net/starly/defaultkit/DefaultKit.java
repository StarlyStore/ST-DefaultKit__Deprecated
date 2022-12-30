package net.starly.defaultkit;

import net.starly.core.data.Config;
import net.starly.defaultkit.command.DefaultKitCmd;
import net.starly.defaultkit.event.InventoryCloseListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class DefaultKit extends JavaPlugin {

    public static DefaultKit plugin;
    private Logger log = Bukkit.getLogger();


    @Override
    public void onEnable() {
        plugin = this;
        init();
    }


    /**
     * 플러그인 정보를 가져옵니다.
     */
    public void init() {

        // DEPENDENCY
        if(Bukkit.getPluginManager().getPlugin("ST-Core") == null) {
            log.warning("[" + plugin.getName() + "] ST-Core 플러그인이 적용되지 않았습니다! 플러그인을 비활성화합니다.");
            log.warning("[" + plugin.getName() + "] 다운로드 링크 : &fhttps://discord.gg/TF8jqSJjCG");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        // CONFIG
        Config config = new Config("config", this);
        config.loadDefaultPluginConfig();

        // EVENT
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), this);

        // COMMAND
        Bukkit.getPluginCommand("defaultkit").setExecutor(new DefaultKitCmd());
    }
}
