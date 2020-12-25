package com.granddad.dbounties.commands;

import com.granddad.dbounties.DBounties;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

public class BountyGUICommand implements CommandExecutor
{
    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        DBounties.bgui.openInventory((HumanEntity) ((Player) sender));
        return true;
    }
}
