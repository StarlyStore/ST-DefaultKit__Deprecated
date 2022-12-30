package net.starly.defaultkit.data;

import net.starly.core.data.Config;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.starly.defaultkit.DefaultKit.plugin;

public class DefaultKitData {


    private Player player;


    public DefaultKitData(Player player) {
        this.player = player;
    }


    /**
     * 기본 키트를 지급받습니다.
     */
    public void getDefaultKit() {
        Config config = new Config("kit", plugin);
        ConfigurationSection section = config.getConfig().getConfigurationSection("kit.items");

        if(section.getKeys(false).size() > 36 - Arrays.stream(player.getInventory().getStorageContents()).filter(item -> item != null).count() ) {
            player.sendMessage("§c인벤토리 공간이 부족합니다.");
            return;
        }

        for(String key : section.getKeys(false)) {
            player.getInventory().addItem(section.getItemStack(key));
        }
        player.sendMessage("§a기본 키트를 지급받았습니다.");
    }


    /**
     * 플레이어의 기본 키트를 설정합니다.
     */
    public void openDefaultKitSetting() {

        Inventory inv = Bukkit.createInventory(null, 36, "키트 설정");

        Config config = new Config("kit", plugin);

        ConfigurationSection section = config.getConfig().getConfigurationSection("kit.items");

        if (section != null) {
            for (String key : section.getKeys(false)) {
                inv.setItem(Integer.parseInt(key), section.getItemStack(key));
            }
        }

        player.openInventory(inv);
    }


    /**
     * 키트 설정을 닫았을 때 | 아이템을 저장합니다.
     *
     * @param event InventoryCloseEvent
     */
    public void setDefaultKit(@NotNull InventoryCloseEvent event) {

        Config kit = new Config("kit", plugin);
        Inventory inv = event.getInventory();

        if (event.getView().getTitle().equalsIgnoreCase("키트 설정")) {

            List<ItemStack> itemStacks = new ArrayList<>();

            ConfigurationSection section = kit.getConfig().createSection("kit.items");

            if (event.getInventory().isEmpty()) {
                section.set("items", null);
                kit.saveConfig();
                return;
            }

            for (int i = 0; i < inv.getSize(); i++) {
                ItemStack item = inv.getItem(i);
                if (item != null) {
                    itemStacks.add(item);
                    section.set(String.valueOf(i), item);
                    kit.saveConfig();
                }
            }
        }
    }


    /**
     * 플레이어의 기본 키트를 초기화합니다.
     * @param target 초기화할 플레이어
     */
    public void resetDefaultKit(Player target) {
        Config data = new Config("data/" + target.getUniqueId(), plugin);

        if (target == null) {
            player.sendMessage("§c플레이어를 찾을 수 없습니다.");
        } else {
            player.sendMessage("§a플레이어의 기본 키트를 초기화했습니다.");
            data.setBoolean("defaultkit", false);
            data.saveConfig();
        }
    }
}