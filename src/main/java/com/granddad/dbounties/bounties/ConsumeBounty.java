package com.granddad.dbounties.bounties;

public class ConsumeBounty extends Bounty
{
    public BountyType Type = BountyType.CONSUME;

    public ConsumeBounty(int timer)
    {
        super(timer);
    }
}
