package com.granddad.dbounties;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

import static org.bukkit.Bukkit.getLogger;

public class BountyGUI implements Listener
{
    private final Inventory inv;

    public BountyGUI()
    {
        // Create a new inventory, with no owner (as this isn't a real inventory), a size of nine, called example
        inv = Bukkit.createInventory(null, 9, "BountyGUI");

        // Put the items into the inventory
        initializeItems();
    }

    // You can call this whenever you want to put the items in
    public void initializeItems()
    {
        inv.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE, " "));
        inv.addItem(createGuiItem(Material.ENCHANTED_BOOK, "Weekly 1", "Do thimbg"));
        inv.addItem(createGuiItem(Material.BOOK, "Daily 1", "Do thimbg 2"));
        inv.addItem(createGuiItem(Material.BOOK, "Daily 2", "Do thimbg 3"));
        inv.addItem(createGuiItem(Material.BOOK, "Daily 3", "Do thimbg 4"));
        inv.addItem(createGuiItem(Material.BOOK, "Daily 4", "Do thimbg 5"));
        inv.addItem(createGuiItem(Material.BOOK, "Daily 5", "Do thimbg 6"));
        inv.addItem(createGuiItem(Material.PAPER, "Repeatable", "Do thimbg 7"));
        inv.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "  "));
    }

    // Nice little method to create a gui item with a custom name, and description
    protected ItemStack createGuiItem(final Material material, final String name, final String... lore)
    {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    // You can open the inventory with this
    public void openInventory(final HumanEntity ent)
    {
        ent.openInventory(inv);
    }

    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e)
    {
        if (e.getInventory() != inv)
        {
            return;
        }

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        final Player p = (Player) e.getWhoClicked();

        // Using slots click is a best option for your inventory click's
        p.sendMessage("You clicked at slot " + e.getRawSlot());
    }

    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e)
    {
        if (e.getInventory() == inv)
        {
            e.setCancelled(true);
        }
    }
}