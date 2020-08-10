package ga.ejer.ping;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class CommandPing implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("ping")) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;

                if (strings.length <= 0) {
                    try {
                        Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
                        int ping = (int) entityPlayer.getClass().getField("ping").get(entityPlayer);

                        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "» " + ChatColor.GRAY + "Your ping: " + ChatColor.GOLD + ping);
                        return true;
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                } else if (strings.length == 1) {
                    if (Bukkit.getPlayerExact(strings[0]) == null) {
                        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "» " + ChatColor.RED + "The player " + ChatColor.GOLD + strings[0] + ChatColor.RED + " cannot be found");
                        return true;
                    }
                    Player target = Bukkit.getPlayerExact(strings[0]);

                    try {
                        Object entityPlayer = target.getClass().getMethod("getHandle").invoke(target);
                        int ping = (int) entityPlayer.getClass().getField("ping").get(entityPlayer);

                        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "» " + ChatColor.GOLD + target.getName() + ChatColor.GRAY + "'s ping: " + ChatColor.GOLD + ping);
                        return true;
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                commandSender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "» " + ChatColor.GRAY + "Only players can use this command");
                return true;
            }
        }
        return true;
    }
}