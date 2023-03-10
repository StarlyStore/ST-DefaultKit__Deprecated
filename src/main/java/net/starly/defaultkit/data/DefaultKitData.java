package net.starly.defaultkit.data;

import net.starly.core.data.Config;
import net.starly.defaultkit.DefaultKit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DefaultKitData {
    private final Player player;

    public DefaultKitData(Player player) {
        this.player = player;
    }

    private final StringData stringData = new StringData();

    /**
     * 기본 키트를 지급받습니다.
     */
    public void getDefaultKit() {
        Config config = new Config("kit", DefaultKit.getPlugin());
        ConfigurationSection section = config.getConfig().getConfigurationSection("kit.items");

        Config data = new Config("data/" + player.getUniqueId(), DefaultKit.getPlugin());


        if (!player.hasPermission("starly.defaultkit.get")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.errMsgNoPermission()));
            return;
        }

        if (data.getBoolean("defaultkit")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.errMsgAlreadyGetDefaultKit()));
            return;
        }

        if (section.getKeys(false).size() > 36 - Arrays.stream(player.getInventory().getStorageContents()).filter(Objects::nonNull).count()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.errMsgInventoryFull()));
            return;
        }

        for (String key : section.getKeys(false))
            player.getInventory().addItem(section.getItemStack(key));

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.msgGetDefaultKit()));

        data.setBoolean("defaultkit", true);
        data.saveConfig();
    }

    /**
     * 플레이어의 기본 키트를 설정합니다.
     */
    public void openDefaultKitSetting() {
        Inventory inv = Bukkit.createInventory(null, 36, "키트 설정");
        Config config = new Config("kit", DefaultKit.getPlugin());
        ConfigurationSection section = config.getConfig().getConfigurationSection("kit.items");

        if (section != null)
            for (String key : section.getKeys(false))
                inv.setItem(Integer.parseInt(key), section.getItemStack(key));

        player.openInventory(inv);
    }

    /**
     * 키트 설정을 닫았을 때 | 아이템을 저장합니다.
     *
     * @param event InventoryCloseEvent
     */
    public void setDefaultKit(@NotNull InventoryCloseEvent event) {
        Config kit = new Config("kit", DefaultKit.getPlugin());
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
     *
     * @param target 초기화할 플레이어
     */
    public void resetDefaultKit(@NotNull Player target) {
        Config data = new Config("data/" + target.getUniqueId(), DefaultKit.getPlugin());

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.msgCompleteResetKit()
                .replace("{player}", target.getDisplayName())));
        data.setBoolean("defaultkit", false);
        data.saveConfig();
    }
}
