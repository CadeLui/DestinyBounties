package com.granddad.dbounties.bounties;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class KillBounty extends Bounty
{
    public static BountyType Type = BountyType.KILL;

    public final EntityType ThingToKill;
    public final int Count;

    public KillBounty(int amount, int timer, EntityType thingToKill, int count)
    {
        super(amount, timer);
        ThingToKill = thingToKill;
        Count = count;

        Lore.add("Kill " + "0/" + Count + " " + ThingToKill.name());
        Book = new ItemStack(Material.BOOK);
        Book.setLore(Lore);
    }

    @Override
    public void GiveToPlayer(HumanEntity player) {
        if (PlayersWithBounty.containsKey(player.getUniqueId()))
            return;
        /*
        ItemStack item = new ItemStack(Material.BOOK);
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Kill " + "0/" + Count + " " + ThingToKill.name());
        item.setLore(lore);
         */
        player.getInventory().addItem(Book);
        PlayersWithBounty.put(player.getUniqueId(), 0);
    }

    @EventHandler
    public void onKill(final EntityDeathEvent e)
    {
        if (e == null)
            return;
        if (e.getEntity().getType() != ThingToKill)
            return;
        Player killer = e.getEntity().getKiller();
        Integer a = (Integer) PlayersWithBounty.get(killer.getUniqueId());
        PlayersWithBounty.put(killer.getUniqueId(), ++a);
        for (ItemStack item : killer.getInventory()){
            if (item == null)
                continue;
            if(!item.getType().name().equals("BOOK"))
                continue;
            List<String> lore = item.getLore();
            if (lore.isEmpty())
                continue;
            String objString = lore.get(0);
            if (!objString.startsWith("Kill "))
                continue;
            String[] objs = objString.split(" ");
            int needToKill = Integer.parseInt(objs[1].split("/")[1]);
            if (needToKill != Count)
                continue;
            if (!objs[2].equals(ThingToKill.name()))
                continue;
            ArrayList<String> newLore = new ArrayList<>();
            newLore.add("Kill " + a + "/" + Count + " " + ThingToKill.name());
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
        if (!objString.startsWith("Kill "))
            return;
        String[] objs = objString.split(" ");
        int needToKill = Integer.parseInt(objs[1].split("/")[1]);
        if (needToKill != Count)
            return;
        if (!objs[2].equals(ThingToKill.name()))
            return;

        int a = (int)PlayersWithBounty.get(e.getPlayer().getUniqueId());
        if (a >= Count) {
            e.getPlayer().getInventory().remove(e.getPlayer().getInventory().getItemInMainHand());
            super.Submit(e.getPlayer());
            // you win
            Bukkit.broadcastMessage(ChatColor.DARK_RED + (e.getPlayer().getDisplayName() + " has wasted time"));
        }
    }
}
