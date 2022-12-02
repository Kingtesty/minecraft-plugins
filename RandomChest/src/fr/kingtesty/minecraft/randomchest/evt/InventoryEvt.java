package fr.kingtesty.forrun.randomchest.evt;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.kingtesty.forrun.randomchest.Randomchest;
import fr.kingtesty.forrun.randomchest.utils.Recompense;
import fr.kingtesty.forrun.randomchest.utils.RecompenseManager;

public class InventoryEvt implements Listener {
	
	@EventHandler
	public void onInventoryClickItem(InventoryClickEvent event) {
	    if (event.getView().getTopInventory().getTitle().equals(
	    		ChatColor.GRAY + "[" 
				+ ChatColor.YELLOW + "RandomChest" 
				+ ChatColor.GRAY + "] "
				+ "Recompenses"
				)) {
			if (event.getWhoClicked().isOp()||event.getWhoClicked().hasPermission("forrun.RandomChest.admin")){}else{event.setCancelled(true);event.getWhoClicked().closeInventory();return;}
	    	event.setCancelled(true);
	        if (event.isRightClick())
	        {
	        	if (event.getCurrentItem() != null) {
	        		if (event.getCurrentItem().getType() == org.bukkit.Material.AIR)
	        			return;
        			List<String> l = event.getCurrentItem().getItemMeta().getLore();
        			String id = l.get(3).replaceAll("#", "");

	        		Randomchest.recompenses.getConfig().set(id, "");
	        		Randomchest.recompenses.save();
	        		HashMap<Integer, Recompense> h = new HashMap<Integer, Recompense>();
	        		int c = 0;
					for (Iterator<String> i = Randomchest.recompenses.getConfig().getKeys(false).iterator(); i.hasNext();)
					{
						String s = (String)i.next();
						String d = (String)Randomchest.recompenses.getConfig().get(s);
						if (!d.equals("")) {
							c++;
							Recompense r = new Recompense(d);
							h.put(c, r);
						}
					}
					Randomchest.recompenses.erase();
					Iterator iterator = h.entrySet().iterator();
					while (iterator.hasNext()) {
						Map.Entry entry = (Map.Entry) iterator.next();
						int key = (int) entry.getKey();
						Recompense value = (Recompense) entry.getValue();
						Randomchest.recompenses.getConfig().set("" + key, value.toString());
					}
					Randomchest.recompenses.save();
					Randomchest.RecompensesId = c;
					event.getWhoClicked().closeInventory();
						
					RecompenseManager.OpenRecompenseList((Player)event.getWhoClicked());
	        		
	        	}
	        }
	    }
	}	
}
