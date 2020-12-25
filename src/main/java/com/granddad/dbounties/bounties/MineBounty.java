package com.granddad.dbounties.bounties;

import org.bukkit.entity.HumanEntity;

public class MineBounty extends Bounty
{
    public static BountyType Type = BountyType.MINE;

    public MineBounty(int timer)
    {
        super(timer);
    }

    public void GiveToPlayer(HumanEntity player) {

    }
}
