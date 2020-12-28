package com.granddad.dbounties.bounties;

import com.granddad.dbounties.DBounties;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public abstract class Bounty implements Listener {
    public final int Timer;

    public static BountyType Type = BountyType.NONE;
    public static Economy Econ;

    public final HashMap<UUID, Object> PlayersWithBounty;
    public final int Amount;

    public ItemStack Book;
    public ArrayList<String> Lore = new ArrayList<>();

    public Bounty(int amount, int timer)
    {
        this.Timer = timer;
        this.Amount = amount;

        PlayersWithBounty = new HashMap<>();

        DBounties.Instance.getServer().getPluginManager().registerEvents(this, DBounties.Instance);
    }

    public abstract void GiveToPlayer(HumanEntity player);

    public void Submit(OfflinePlayer player)
    {
        Econ.depositPlayer(player, Amount);
        UUID playerUuid = player.getUniqueId();
        if (PlayersWithBounty.containsKey(playerUuid))
            PlayersWithBounty.remove(playerUuid);
    }
}
