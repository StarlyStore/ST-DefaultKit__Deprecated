package net.starly.defaultkit.command;

import net.starly.defaultkit.data.DefaultKitData;
import net.starly.defaultkit.data.StringData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DefaultKitCmd implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return true;

        DefaultKitData kit = new DefaultKitData(player);
        if (args.length == 0) {
            kit.getDefaultKit();
            return true;
        }

        StringData stringData = new StringData();

        if (!player.hasPermission("starly.defaultkit.admin")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.errMsgNoPermission()));

            return true;
        }

        switch (args[0].toLowerCase()) {
            case "설정", "setting" -> {
                kit.openDefaultKitSetting();
                return true;
            }

            case "초기화", "reset" -> {
                if (args.length != 2) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.errMsgInvalidCommand()));
                    return true;
                }

                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.errMsgNoFindPlayer()));
                    return true;
                }

                kit.resetDefaultKit(target);
                return true;
            }
        }

        return false;
    }
}
