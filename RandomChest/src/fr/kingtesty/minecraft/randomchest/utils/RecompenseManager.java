package fr.kingtesty.forrun.randomchest.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.kingtesty.forrun.randomchest.Randomchest;

public class RecompenseManager {
	public static int MinimumRecompense = 4;
	public static int MaximumRecompense = 7;

	public static boolean AddRecompense(String[] args, Player player) {
		int chance;
		if (args.length == 3)
		{
			if (Randomchest.isInteger(args[2]))
			{
				chance = Integer.parseInt(args[2]);	
				if (chance > 0 && chance <= 100) 
				{
					if (player.getInventory().getItemInHand() != null && player.getInventory().getItemInHand().getType() != Material.AIR) 
					{

						ItemStack it = player.getInventory().getItemInHand();
						Randomchest.RecompensesId++;
						Randomchest.recompenses.getConfig().set("" + Randomchest.RecompensesId, new Recompense(chance, it).toString());
						Randomchest.recompenses.save();
						player.sendMessage(
							ChatColor.GRAY + "[" 
							+ ChatColor.YELLOW + "RandomChest" 
							+ ChatColor.GRAY + "] "
							+ it.getType().name() + " à été ajouté à la liste des récompenses !");
						return true;
						
					} 
					else 
					{
						player.sendMessage(
								ChatColor.GRAY + "[" 
								+ ChatColor.YELLOW + "RandomChest" 
								+ ChatColor.GRAY + "] "
								+ "Your Hand is empty !");
						return true;
					}
				}
			}
		}
		player.sendMessage(
				ChatColor.RED + "Usage: " 
				+ "/RandomChest items <add/list> [chance]");
		return true;
	}
	public static boolean RecompenseManagement(String[] args, Player player) {
		if (args.length == 3)
		{
			String[] a = null;
			if (args[2].contains(":"))
				a = args[2].split(":");

			if (Randomchest.isInteger(a[0]) && Randomchest.isInteger(a[1]))
			{
				if (!(Integer.parseInt(a[0]) < 1) && !(Integer.parseInt(a[0]) > 27) && !(Integer.parseInt(a[1]) < 1) && !(Integer.parseInt(a[1]) > 27)) 
				{
					Randomchest.recompensesamount.getConfig().set("Minimum-per-chest", a[0]);
					Randomchest.recompensesamount.getConfig().set("Maximum-per-chest", a[1]);
					Randomchest.recompensesamount.save();
					RecompenseManager.MinimumRecompense = Integer.parseInt(a[0]);
					RecompenseManager.MaximumRecompense = Integer.parseInt(a[1]);
					player.sendMessage(
							ChatColor.GRAY + "[" 
							+ ChatColor.YELLOW + "RandomChest" 
							+ ChatColor.GRAY + "] "
							+ "Vous avez défini le nombres de récompenses par coffres à "
							+ a[0] + " Minimum "
							+ "et à " + a[1] + " Maximum"
							+ " !"
							);
					return true;
				}
			}
		}
		player.sendMessage(
				ChatColor.RED + "Usage: " 
				+ "/RandomChest items amount <minimum:maximum>");
		return true;
	}
	public static void OpenRecompenseList(Player player) {
		Inventory inv = Bukkit.createInventory(null, 54,
				ChatColor.GRAY + "[" 
				+ ChatColor.YELLOW + "RandomChest" 
				+ ChatColor.GRAY + "] "
				+ "Recompenses"
				);
		for (Iterator<String> i = Randomchest.recompenses.getConfig().getKeys(false).iterator(); i.hasNext();)
		{
			String s = (String)i.next();
			String d = (String)Randomchest.recompenses.getConfig().get(s);
			Recompense r = new Recompense(d);
			int chance = r.getChance();
			ItemStack it = r.getItemStack();
			ItemMeta itmeta = it.getItemMeta();
			ArrayList<String> lore = new ArrayList<String>();
				
			lore.add("Left Click to modify the chance");
			lore.add("Right Click to remove");
			lore.add("Looting Chance: " + chance + "%");
			lore.add("#" + s);
			itmeta.setLore(lore);
			it.setItemMeta(itmeta);
			              
			inv.addItem(it);
		}
		player.openInventory(inv);

	}
	public static ArrayList<ItemStack> getRecompenses(int n) {
		ArrayList<ItemStack> itemst = new ArrayList<ItemStack>();

		for (Iterator<String> i1 = Randomchest.recompenses.getConfig().getKeys(false).iterator(); i1.hasNext();)
		{
			String s = (String)i1.next();
			String d = (String)Randomchest.recompenses.getConfig().get(s);
			Recompense r = new Recompense(d);
			int chance = r.getChance();
			ItemStack it = r.getItemStack();
			for (int i = 0; i < chance; i++)  
			    itemst.add(it);
		}
		
	    ArrayList<ItemStack> resp = new ArrayList<ItemStack>();
	    
	    Collections.shuffle(itemst, new Random(System.currentTimeMillis()));
	    for (int i1 = 0; (i1 < n) && (i1 < itemst.size()); i1++) {
	      resp.add((ItemStack)itemst.get(i1));
	    }
	    return resp;
	}
}
