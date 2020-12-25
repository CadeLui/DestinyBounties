package com.granddad.dbounties;

import com.granddad.dbounties.commands.BountyGUICommand;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public final class DBounties extends JavaPlugin
{
    public static BountyGUI bgui;

    @Override
    public void onEnable()
    {
        Economy econ = getServer().getServicesManager().getRegistration(Economy.class).getProvider();
        bgui = new BountyGUI(econ);

        // Plugin startup logic
        getLogger().info("Entering orbit...");
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
