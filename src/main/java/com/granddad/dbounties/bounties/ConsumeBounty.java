package com.granddad.dbounties.bounties;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ConsumeBounty extends Bounty
{
    public BountyType Type = BountyType.CONSUME;

    public final Material Item;
    public final int Capacity;

    public ConsumeBounty(int timer, Material item, int capacity)
    {
        super(timer);

        Item = item;
        Capacity = capacity;
    }

    public void GiveToPlayer(HumanEntity player) {
        ItemStack item = new ItemStack(Material.BOOK);
        ArrayList<String> lore = new ArrayList<>();
        lore.add("bru hg");
        item.setLore(lore);
        player.getInventory().addItem(item);
    }
}
