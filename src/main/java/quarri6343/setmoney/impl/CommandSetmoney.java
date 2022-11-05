package quarri6343.setmoney.impl;

import com.google.common.collect.ImmutableList;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import quarri6343.setmoney.Setmoney;
import quarri6343.setmoney.api.CommandBase;

import java.util.List;

public class CommandSetmoney extends CommandBase {
    
    private static final String commandName="setmoney";
    
    public CommandSetmoney() {
        super(commandName, 1, 1, false);
    }

    @Override
    public boolean onCommand(CommandSender sender, @Nullable String[] arguments) {
        int num = 0;
        try {
            num = Integer.parseInt(arguments[0]);
        } catch (NumberFormatException e) {
            sender.sendMessage(Component.text("数字を入力してください").color(NamedTextColor.RED));
            return true;
        }
        
        for(Player player : Bukkit.getOnlinePlayers()){
            Setmoney.getEconomy().withdrawPlayer(player, Setmoney.getEconomy().getBalance(player));
            Setmoney.getEconomy().depositPlayer(player, num);
        }
        for(OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()){
            Setmoney.getEconomy().withdrawPlayer(offlinePlayer, Setmoney.getEconomy().getBalance(offlinePlayer));
            Setmoney.getEconomy().depositPlayer(offlinePlayer, num);
        }
        
        sender.sendMessage(Component.text("全員の所持金を" + num + "円に設定しました"));
        
        return true;
    }

    @Override
    @NotNull
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        if (args.length == 1) {return ImmutableList.of("<any number>");
        }
        return ImmutableList.of();
    }
}
