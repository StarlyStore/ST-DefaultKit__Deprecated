package net.starly.defaultkit.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DefaultCmdTabComplete implements TabCompleter {


    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player player) {
            if (player.hasPermission("starly.defaultkit.admin")) {
                if (args.length == 1) {
                    return List.of("설정", "초기화");
                } else if(args.length == 2) {
                    if(args[0].equalsIgnoreCase("초기화")) {
                        for(Player target : player.getServer().getOnlinePlayers()) {
                            return List.of(target.getName());
                        }
                    }
                }
            }
        }
        return null;
    }
}
