package com.granddad.dbounties.bounties;

abstract class Bounty
{
    private final int timer;

    public static BountyType Type = BountyType.NONE;

    public Bounty(int timer)
    {
        this.timer = timer;
    }
}