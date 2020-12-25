package com.granddad.dbounties.bounties;

abstract class Bounty
{
    private final int timer;

    public Bounty(int timer)
    {
        this.timer = timer;
    }
}
