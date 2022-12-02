package fr.kingtesty.forrun.randomchest.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.kingtesty.forrun.randomchest.utils.RecompenseManager;

public class RandomChestCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	    if (sender.hasPermission("forrun.randomchest.config.admin") || sender.isOp())
	    {
			if (args.length > 0)
			{
				if (args[0].equalsIgnoreCase("items"))
				{
					if (sender instanceof Player)
					{
						if (args.length >= 2) 
						{
							if (args[1].equalsIgnoreCase("add"))
							{
								return RecompenseManager.AddRecompense(args, (Player)sender);
							}
		
							if (args[1].equalsIgnoreCase("list"))
							{
								RecompenseManager.OpenRecompenseList((Player)sender);
								return true;
							}
							if (args[1].equalsIgnoreCase("amount"))
							{
								return RecompenseManager.RecompenseManagement(args, (Player)sender);
							}
						}
						((Player) sender).sendMessage(
								ChatColor.RED + "Usage: " 
								+ "/RandomChest items <add/list/amount> [chance]");
						return true;
					}
				}
			}
	    }
		return false;
	}

}
