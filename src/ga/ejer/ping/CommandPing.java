package ga.ejer.ping;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CommandPing implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("ping")) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;

                if (strings.length <= 0) {
                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "» " + ChatColor.GRAY + "Your ping: " + ChatColor.GOLD + ((CraftPlayer) player).getHandle().ping);
                    return true;
                } else if (strings.length == 1) {
                    if (Bukkit.getPlayerExact(strings[0]) == null) {
                        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "» " + ChatColor.RED + "The player " + ChatColor.GOLD + strings[0] + ChatColor.RED + " cannot be found");
                        return true;
                    }
                    Player target = Bukkit.getPlayerExact(strings[0]);

                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "» " + ChatColor.GOLD + target.getName() + ChatColor.GRAY + "'s ping: " + ChatColor.GOLD + ((CraftPlayer) target).getHandle().ping);
                    return true;
                }
            } else {
                commandSender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "» " + ChatColor.GRAY + "Only players can use this command");
                return true;
            }
        }
        return true;
    }
}