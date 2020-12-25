package com.granddad.dbounties;

import com.granddad.dbounties.commands.BountyGUICommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class DBounties extends JavaPlugin
{
    public static BountyGUI bgui = new BountyGUI();

    @Override
    public void onEnable()
    {
        // Plugin startup logic
        getLogger().info("Entering orbit...");
        this.getCommand("bounties").setExecutor(new BountyGUICommand());
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
