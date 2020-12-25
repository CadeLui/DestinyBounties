package com.granddad.dbounties.bounties;

import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;

public class KillBounty extends Bounty
{
    public static BountyType Type = BountyType.KILL;

    public final Entity ThingToKill;
    public final int Count;

    public KillBounty(int timer, Entity thingToKill, int count)
    {
        super(timer);

        ThingToKill = thingToKill;
        Count = count;
    }

    public void GiveToPlayer(HumanEntity player) {

    }
}
