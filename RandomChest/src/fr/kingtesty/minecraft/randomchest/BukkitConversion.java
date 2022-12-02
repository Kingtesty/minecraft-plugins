package fr.kingtesty.forrun.randomchest.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

public class BukkitConversion
{
	public static String ItemStacktoBase64(ItemStack inv)
	{
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	    try {
		    BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
		    dataOutput.writeObject(inv);
		    dataOutput.close();
		} catch (IOException e) {
		}
	    
	    return Base64.getEncoder().encodeToString(outputStream.toByteArray());
	}
  
	public static ItemStack ItemStackfromBase64(String data)
	{
	    ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(data));
	    ItemStack it = null;

	    try {
		    BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
		    it = (ItemStack)dataInput.readObject();
		    dataInput.close();

	    } catch (IOException | ClassNotFoundException e) {
		}
	    
	    return it;
	}
}
