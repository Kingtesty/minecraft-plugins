package fr.kingtesty.forrun.randomchest.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.kingtesty.forrun.randomchest.utils.RecompenseManager;
import fr.kingtesty.forrun.randomchest.utils.SpawnChest;

public class SpawnChestCmd
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
	  if (sender.hasPermission("forrun.randomchest.spawnchest.admin") || sender.isOp())
	  {
	    int n = 5;
	    if (args.length > 0) {
	    	if (args[0].equalsIgnoreCase("clean"))
	    	{
	    		SpawnChest.clean();
	    		sender.sendMessage(ChatColor.RED + "Vous avez clear tous les coffres");
	    		return true;
	    	}
	    		
	    	try
	    	{
	    		n = Integer.parseInt(args[0]);
	    	}
	    	catch (NumberFormatException e)
	    	{
	    		sender.sendMessage(ChatColor.RED + "Vous n'avez pas entré un nombre correct");
	    		return false;
	    	}
	    }
	    else
	    {
	    	n =  RecompenseManager.MinimumRecompense + (int)(Math.random() * (RecompenseManager.MaximumRecompense - RecompenseManager.MinimumRecompense));
	    }
	    SpawnChest.addChest(new SpawnChest(n));
	    return true;
	  } 
	  else 
	  {
		    sender.sendMessage(ChatColor.RED + "Vous n'avez pas la permissions de faire ça");
		    return true;
	  }
  }
}
