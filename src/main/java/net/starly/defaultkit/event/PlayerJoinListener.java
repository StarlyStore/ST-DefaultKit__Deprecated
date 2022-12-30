package net.starly.defaultkit.event;

import net.starly.core.data.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static net.starly.defaultkit.DefaultKit.plugin;

public class PlayerJoinListener implements Listener {


    /**
     * 플레이어가 접속했을 때 | 플레이어의 기본 키트를 설정합니다.
     * @param event PlayerJoinEvent
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Config config = new Config("data/" + player.getUniqueId(), plugin);

        if(!config.isFileExist()) {
            config.setBoolean("defaultkit", false);
            config.saveConfig();
        }
    }
}
