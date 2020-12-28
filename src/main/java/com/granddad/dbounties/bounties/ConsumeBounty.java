package com.granddad.dbounties.bounties;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;

public class ConsumeBounty extends Bounty
{
    public BountyType Type = BountyType.CONSUME;

    public final Material Item;
    public final int Capacity;

    public ConsumeBounty(int amount, int timer, Material item, int capacity)
    {
        super(amount, timer);
        Item = item;
        Capacity = capacity;

        Lore.add("Obtain " + Capacity + " " + Item.name());
        Lore.add("These will be consumed.");
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
        lore.add("Obtain " + Capacity + " " + Item.name());
        lore.add("These will be consumed.");
        item.setLore(lore);
         */
        player.getInventory().addItem(Book);
        PlayersWithBounty.put(player.getUniqueId(), null);
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
        if (!objString.startsWith("Obtain "))
            return;
        String[] objs = objString.split(" ");
        if (Integer.parseInt(objs[1]) != Capacity)
            return;
        if (Material.valueOf(objs[2]) != Item)
            return;

        Player player = e.getPlayer();
        PlayerInventory inv = player.getInventory();
        if (!inv.contains(Item, Capacity)) {
            player.sendRawMessage("You don't have " + Capacity + " " + Item.name());
            return;
        }
        int gotten = 0;
        while (gotten < Capacity) {
            int loc = inv.first(Item);
            ItemStack stack = inv.getItem(loc);
            if (stack.getAmount() >= Capacity - gotten) {
                stack.setAmount(stack.getAmount() - Capacity);
                break;
            }
            gotten += stack.getAmount();
            stack.setAmount(0);
        }
        inv.remove(inv.getItemInMainHand());
        super.Submit(player);
        Bukkit.broadcastMessage(ChatColor.DARK_RED + (e.getPlayer().getDisplayName() + " has wasted time"));
    }
}
