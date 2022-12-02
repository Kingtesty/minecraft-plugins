package fr.kingtesty.forrun.randomchest.cmds;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.kingtesty.forrun.randomchest.Randomchest;
import fr.kingtesty.forrun.randomchest.bdd.BDD_Request;
import fr.kingtesty.forrun.randomchest.utils.ChestLocation;

public class ChestCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		sender.sendMessage(Randomchest.chest_list);
		sender.sendMessage(Randomchest.chest_list2);
		ArrayList<ChestLocation> arl = BDD_Request.getChests();
		Iterator<ChestLocation> it = arl.iterator();
		while (it.hasNext())
		{
			ChestLocation cl = it.next();
			sender.sendMessage(ChatColor.YELLOW + "X:" + cl.getX() + " Y:" + cl.getY() + " Z:" + cl.getZ());
		}
		return true;
	}
}
