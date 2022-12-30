package net.starly.defaultkit.event;

import net.starly.defaultkit.data.DefaultKitData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {


    /**
     * 플레이어가 인벤토리를 닫았을 때 | 플레이어의 기본 키트를 설정합니다.
     * @param event InventoryCloseEvent
     */
    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        DefaultKitData kit = new DefaultKitData(player);

        kit.setDefaultKit(event);

    }
}
