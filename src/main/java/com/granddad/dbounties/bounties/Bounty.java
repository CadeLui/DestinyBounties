package com.granddad.dbounties.bounties;

import com.granddad.dbounties.DBounties;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Listener;

public abstract class Bounty implements Listener {
    private final int timer;

    public static BountyType Type = BountyType.NONE;

    public Bounty(int timer)
    {
        this.timer = timer;

        DBounties.Instance.getServer().getPluginManager().registerEvents(this, DBounties.Instance);
    }

    public abstract void GiveToPlayer(HumanEntity player);
}
