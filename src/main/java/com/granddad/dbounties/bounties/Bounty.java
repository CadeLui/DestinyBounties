package com.granddad.dbounties.bounties;

import com.granddad.dbounties.DBounties;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.UUID;

public abstract class Bounty implements Listener {
    public final int Timer;

    public static BountyType Type = BountyType.NONE;
    public static Economy Econ;

    public final HashMap<UUID, Object> PlayersWithBounty;
    public final int Amount;

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
