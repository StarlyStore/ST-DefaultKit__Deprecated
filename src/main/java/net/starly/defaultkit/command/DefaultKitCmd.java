package net.starly.defaultkit.command;

import net.starly.defaultkit.data.DefaultKitData;
import net.starly.defaultkit.data.StringData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultKitCmd implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player player) {

            DefaultKitData kit = new DefaultKitData(player);

            if (args.length == 0) {
                kit.getDefaultKit();
                return true;
            }

            if (player.hasPermission("starly.defaultkit.admin")) {
                switch (args[0]) {

                    case "설정", "setting" -> {
                        kit.openDefaultKitSetting();
                        return true;
                    }

                    case "초기화", "reset" -> {
                        Player target = Bukkit.getPlayer(args[1]);
                        kit.resetDefaultKit(target);
                        return true;
                    }
                }
            } else {
                StringData stringData = new StringData();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.errMsgNoPermission()));
            }
        }
        return false;
    }
}
