package com.granddad.dbounties;

import com.granddad.dbounties.commands.BountyGUICommand;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.java.JavaPlugin;

public final class DBounties extends JavaPlugin
{
    public static BountyGUI bgui;
    public static DBounties Instance;

    @Override
    public void onEnable()
    {
        getLogger().info("Entering orbit...");
        Instance = this;

        Economy econ = getServer().getServicesManager().getRegistration(Economy.class).getProvider();
        bgui = new BountyGUI(econ);

        // Plugin startup logic
        getLogger().info("Registering commands and events...");
        this.getCommand("bounties").setExecutor(new BountyGUICommand(econ));
        getServer().getPluginManager().registerEvents(bgui, this);
        getLogger().info("Orbit entered.");
    }

    @Override
    public void onDisable()
    {
        getLogger().info("Exiting orbit...");
        getLogger().info("Orbit exited.");
    }
}
