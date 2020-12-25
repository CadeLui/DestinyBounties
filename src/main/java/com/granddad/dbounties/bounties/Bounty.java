package com.granddad.dbounties.bounties;

import org.bukkit.entity.HumanEntity;

public abstract class Bounty
{
    private final int timer;

    public static BountyType Type = BountyType.NONE;

    public Bounty(int timer)
    {
        this.timer = timer;
    }

    public abstract void GiveToPlayer(HumanEntity player);
}