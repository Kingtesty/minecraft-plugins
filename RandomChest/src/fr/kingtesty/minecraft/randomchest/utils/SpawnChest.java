package fr.kingtesty.forrun.randomchest.utils;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import fr.kingtesty.forrun.randomchest.Randomchest;
import fr.kingtesty.forrun.randomchest.bdd.BDD_Request;
import fr.kingtesty.forrun.randomchest.evt.BlockEvt;

public class SpawnChest
{

  public static boolean isSpawnChest(Location loc)
  {
    return getChest(loc) != null;
  }

  public static SpawnChest getChest(Location loc)
  {
    for (SpawnChest c : chests) {
      if (c.loc.equals(loc)) {
        return c;
      }
    }
    return null;
  }
  
  public static void removeChest(SpawnChest sc)
  {
    chests.remove(sc);
  }
  
  public static void addChest(SpawnChest sc)
  {
    chests.add(sc);
  }
  
  public static void clean()
  {
    for (SpawnChest sc : chests) {
      sc.getBlock().setType(Material.AIR);
    }
    BDD_Request.clearLocationChest();
    chests.clear();
  }
  
  private static ArrayList<SpawnChest> chests = new ArrayList<SpawnChest>();
  
  private int n;
  private int x;
  private int y;
  private int z;
  private Location loc;
  private Block block;
  
  public static void clearListChest() {
	  chests.clear();
  }
  
  public Block getBlock()
  {
    return this.block;
  }
  
  public void drop()
  {
    this.block.setType(Material.AIR);
    for (ItemStack item : RecompenseManager.getRecompenses(this.n)) {
    	Randomchest.world.dropItem(this.loc, item);
    }
  }
  public SpawnChest(String a, String b, String c, String nombre)
  {
	  this.x = Integer.parseInt(a);
	  this.y = Integer.parseInt(b);
	  this.z = Integer.parseInt(c);
	  this.n = Integer.parseInt(nombre);

	  this.block = Randomchest.world.getBlockAt(x, y, z);
	  this.loc = this.block.getLocation();
	  
	  BlockEvt.setcoord(x, y, z);
  }
  public SpawnChest(int nombre)
  {
    int xplus = new Random().nextInt(5501) + 500;
    int xmoins = -new Random().nextInt(5501) + 500;
    
    int zplus = new Random().nextInt(5501) + 500;
    int zmoins = -new Random().nextInt(5501) + 500;
    
    this.x = (new Random().nextBoolean() ? xplus : xmoins);
    this.z = (new Random().nextBoolean() ? zplus : zmoins);
    
    this.n = nombre;
    
    this.block = Randomchest.world.getHighestBlockAt(this.x, this.z);
    this.y = this.block.getY();
    this.loc = this.block.getLocation();
    this.block.setType(Material.CHEST);
    
    BlockEvt.setcoord(x, y, z);
    
    Bukkit.broadcastMessage(Randomchest.chest_spawn);
    Bukkit.broadcastMessage(toString());
    BDD_Request.saveLocationChest("" + x, "" + y, "" + z, "" + n);
  }
  
  public String toString()
  {
	  return Randomchest.chest_spawn2.replaceAll("%X%", "" + this.x).replaceAll("%Y%", "" + this.y).replaceAll("%Z%", "" + this.z);
  }

}
