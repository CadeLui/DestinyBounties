package com.granddad.dbounties.bounties;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MineBounty extends Bounty
{
    public static BountyType Type = BountyType.MINE;
    public final Material BlockToBreak;
    public final int Count;

    public MineBounty(int amount, int timer, Material blockToBreak, int count)
    {
        super(amount, timer);

        BlockToBreak = blockToBreak;
        Count = count;
    }

    @Override
    public void GiveToPlayer(HumanEntity player)
    {
        if (PlayersWithBounty.containsKey(player.getUniqueId()))
            return;
        ItemStack item = new ItemStack(Material.BOOK);
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Mine " + "0/" + Count + " " + BlockToBreak.name());
        item.setLore(lore);
        player.getInventory().addItem(item);
        PlayersWithBounty.put(player.getUniqueId(), 0);
    }

    @EventHandler
    public void BlockBreakEvent(BlockBreakEvent e)
    {
        Material BlockBroken = e.getBlock().getType();
        if (BlockBroken != BlockToBreak)
        {
            return;
        }

        Player miner = e.getPlayer();
        if (miner == null)
            return;
        Integer a = (Integer) PlayersWithBounty.get(miner.getUniqueId());
        PlayersWithBounty.put(miner.getUniqueId(), ++a);

        for (ItemStack item : miner.getInventory()){
            if (item == null)
                continue;
            if (item.getType() == null)
                continue;
            if(!item.getType().name().equals("BOOK"))
                continue;
            List<String> lore = item.getLore();
            if (lore.isEmpty())
                continue;
            String objString = lore.get(0);
            if (!objString.startsWith("Mine "))
                continue;
            String[] objs = objString.split(" ");
            int needToKill = Integer.parseInt(objs[1].split("/")[1]);
            if (needToKill != Count)
                continue;
            if (!objs[2].equals(BlockToBreak.name()))
                continue;
            ArrayList<String> newLore = new ArrayList<>();
            newLore.add("Mine " + a + "/" + Count + " " + BlockToBreak.name());
            item.setLore(newLore);
        }

    }

    @EventHandler
    public void onRightClick(final PlayerInteractEvent e) {
        if (!PlayersWithBounty.containsKey(e.getPlayer().getUniqueId()))
            return;
        if (!e.getAction().name().equals("RIGHT_CLICK_AIR"))
            return;
        ItemStack item = e.getItem();
        if (!item.getType().name().equals("BOOK"))
            return;
        List<String> lore = item.getLore();
        if (lore.isEmpty())
            return;
        String objString = lore.get(0);
        if (!objString.startsWith("Mine "))
            return;
        String[] objs = objString.split(" ");
        int needToKill = Integer.parseInt(objs[1].split("/")[1]);
        if (needToKill != Count)
            return;
        if (!objs[2].equals(BlockToBreak.name()))
            return;

        int a = (int)PlayersWithBounty.get(e.getPlayer().getUniqueId());
        if (a >= Count) {
            e.getPlayer().getInventory().remove(e.getPlayer().getInventory().getItemInMainHand());
            super.Submit(e.getPlayer());
            // you win
            Bukkit.broadcastMessage(ChatColor.DARK_AQUA + (e.getPlayer().getDisplayName() + " listens to 100 gecs"));
        }
    }
}
