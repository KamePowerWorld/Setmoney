package quarri6343.setmoney;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import quarri6343.setmoney.impl.CommandSetmoney;

public final class Setmoney extends JavaPlugin {

    private static Setmoney instance;
    private static Economy economy = null;

    public Setmoney() {
        instance = this;
        new CommandSetmoney();
    }
    
    public static Setmoney getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!setupEconomy()) {
            Bukkit.getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    public static Economy getEconomy() {
        return economy;
    }
}
