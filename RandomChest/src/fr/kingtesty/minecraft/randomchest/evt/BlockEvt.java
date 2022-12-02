package fr.kingtesty.forrun.randomchest.evt;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.kingtesty.forrun.randomchest.Randomchest;
import fr.kingtesty.forrun.randomchest.bdd.BDD_Request;
import fr.kingtesty.forrun.randomchest.utils.SpawnChest;

public class BlockEvt
  implements Listener
{
	  public static int x;
	  public static int y;
	  public static int z;
	  public static void setcoord(int a, int b, int c)
	  {
		  x=a;
		  y=b;
		  z=c;
	  }
	  @EventHandler
	  public void onClick(PlayerInteractEvent evt)
	  {
		  if ((evt.getAction().equals(Action.RIGHT_CLICK_BLOCK)) || (evt.getAction().equals(Action.LEFT_CLICK_BLOCK)))
		  {
			  if (SpawnChest.isSpawnChest(evt.getClickedBlock().getLocation()))
			  {
				  if ((evt.getPlayer().isOp()) && (!evt.getPlayer().isSneaking()))
				  {
					  evt.setCancelled(true);
					  return;
				  }
				  SpawnChest sc = SpawnChest.getChest(evt.getClickedBlock().getLocation());
				  BDD_Request.deleteChestByLocation("" + sc.getBlock().getX(), "" + sc.getBlock().getZ());
				  sc.drop();
				  SpawnChest.removeChest(sc);
				  evt.setCancelled(true);
				  Bukkit.broadcastMessage(Randomchest.chest_recompense.replaceAll("%player%", evt.getPlayer().getName()).replaceAll("%X%", "" + BlockEvt.x).replaceAll("%Y%", "" + BlockEvt.y).replaceAll("%Z%", "" + BlockEvt.z) );
				  Randomchest.world.playSound(evt.getPlayer().getLocation(), Sound.AMBIENCE_CAVE, 10.0F, 1.0F);
				  return;
			  }
			  return;
		  }
	  }
}
