package com.granddad.dbounties.commands;

import com.granddad.dbounties.DBounties;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public class BountyGUICommand implements CommandExecutor
{
    private final Economy econ;

    public BountyGUICommand(Economy econ)
    {
        this.econ = econ;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        DBounties.bgui.openInventory((HumanEntity) ((Player) sender));
        return true;
    }
}
