package fr.kingtesty.forrun.randomchest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import fr.kingtesty.forrun.randomchest.bdd.BDD;
import fr.kingtesty.forrun.randomchest.bdd.BDD_Request;
import fr.kingtesty.forrun.randomchest.cmds.ChestCmd;
import fr.kingtesty.forrun.randomchest.cmds.RandomChestCmd;
import fr.kingtesty.forrun.randomchest.cmds.SpawnChestCmd;
import fr.kingtesty.forrun.randomchest.evt.BlockEvt;
import fr.kingtesty.forrun.randomchest.evt.InventoryEvt;
import fr.kingtesty.forrun.randomchest.utils.ChestLocation;
import fr.kingtesty.forrun.randomchest.utils.Config;
import fr.kingtesty.forrun.randomchest.utils.RecompenseManager;
import fr.kingtesty.forrun.randomchest.utils.SpawnChest;

public class Randomchest
  extends JavaPlugin
{
	public static World world;
	public static int RecompensesId = 0;
	public static Config recompenses;
	public static Config recompensesamount;
	public static Config lang;
	
	public static String chest_spawn;
	public static String chest_spawn2;
	public static String chest_list;
	public static String chest_list2;
	public static String chest_recompense;

	private static JavaPlugin plugin;
   
	public static JavaPlugin getPlugin()
	{
		return plugin;
	}
  
	public void onEnable()
	{
		plugin = this;
		
		saveDefaultConfig();
		if (!BDD.init(
				getConfig().getString("mysql-host", "localhost"), 
				getConfig().getString("mysql-port", "3306"), 
				getConfig().getString("mysql-db", "Plugins"), 
				getConfig().getString("mysql-username", "RandomChest"), 
				getConfig().getString("mysql-password", "aXbVzF4")))
				return;
		 recompenses = new Config("recompenses");
		 recompensesamount = new Config("amount");
		 lang = new Config("lang");
		    
		 
		 chest_spawn = lang.getConfig().getString("chest-spawn", "§7[§eCoffres§7] §7Apparition d'un §ebutin ancestral dans le Monde §7!");
		 lang.getConfig().set("chest-spawn", chest_spawn);
		    
		 chest_spawn2 = lang.getConfig().getString("chest-spawn2", "§cX:§a%X% §cY:§a%Y% §cZ:§a%Z%");
		 lang.getConfig().set("chest-spawn2", chest_spawn2);
		    
		 chest_list = lang.getConfig().getString("chest-list", "§e______________.[ §aCoffres Ancestraux§e ].______________");
		 lang.getConfig().set("chest-list", chest_list);
		    
		 chest_list2 = lang.getConfig().getString("chest-list2", "§6Liste des coffres ancestraux :");
		 lang.getConfig().set("chest-list2", chest_list2);
	
		 chest_recompense = lang.getConfig().getString("chest-recompense", "§7[§eCoffres§7] Récupération du §ebutin ancestral §7dans le §eMonde §7par §c§l%player% §7! (§cX:§a%X% §cY:§a%Y% §cZ:§a%Z%§7)");
		 lang.getConfig().set("chest-recompense", chest_recompense);
		 lang.save();
		    
		 for (Iterator<String> i = Randomchest.recompenses.getConfig().getKeys(false).iterator(); i.hasNext();)
		 {
			 i.next();
			 RecompensesId++;
		 }
			
		 if (isInteger(recompensesamount.getConfig().getString("Minimum-per-chest"))) {
			 RecompenseManager.MinimumRecompense = Integer.parseInt(recompensesamount.getConfig().getString("Minimum-per-chest", "4"));
		 } else
			 RecompenseManager.MinimumRecompense = 4; 
	
		 if (isInteger(recompensesamount.getConfig().getString("Maximum-per-chest"))) {
			 RecompenseManager.MaximumRecompense = Integer.parseInt(recompensesamount.getConfig().getString("Maximum-per-chest", "7"));
		 } else
			 RecompenseManager.MaximumRecompense = 7;
		 recompensesamount.save();
		    
		 world = Bukkit.getServer().getWorld(getConfig().getString("world-name", "world"));
		 SpawnChest.clearListChest();
		 ArrayList<ChestLocation> arlst = BDD_Request.getChests();
		 Iterator<ChestLocation> itarlst = arlst.iterator();
		 while (itarlst.hasNext())
		 {
			 ChestLocation cl = itarlst.next();
			 SpawnChest.addChest(new SpawnChest(cl.getX(), cl.getY(), cl.getZ(), cl.getNombre()));
		 }
		 getCommand("rc").setExecutor(new SpawnChestCmd());
		 getCommand("coffres").setExecutor(new ChestCmd());
		 getCommand("randomchest").setExecutor(new RandomChestCmd());
	
		 Bukkit.getPluginManager().registerEvents(new BlockEvt(), this);
		 Bukkit.getPluginManager().registerEvents(new InventoryEvt(), this);
	}
	
	public void onDisable()
	{
		
	}
	
	public static boolean isInteger(String s) {
	    try {
	    	if (s == null)
	    		return false;
	    	Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
}
