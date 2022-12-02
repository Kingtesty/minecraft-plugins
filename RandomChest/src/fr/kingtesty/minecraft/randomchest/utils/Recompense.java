package fr.kingtesty.forrun.randomchest.utils;

import org.bukkit.inventory.ItemStack;

public class Recompense
{
  private int chance;
  private String ItemStack;
  
  public Recompense(int chance, ItemStack ItemStack)
  {
    this.chance = chance;
    this.ItemStack = BukkitConversion.ItemStacktoBase64(ItemStack);
  }
  
  public Recompense(String a)
  {
	  String[] b = a.split(":");
	  this.ItemStack = b[0];
	  this.chance = Integer.parseInt(b[1]);
  }
  
  public int getChance()
  {
    return this.chance;
  }
  
  public ItemStack getItemStack()
  {
	  return BukkitConversion.ItemStackfromBase64(ItemStack);
  }
  
  public String toString () {
	  
	return ItemStack + ":" + chance;
  }
}
