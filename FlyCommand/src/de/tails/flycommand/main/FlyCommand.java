package de.tails.flycommand.main;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class FlyCommand extends JavaPlugin implements CommandExecutor {

	private static ArrayList<Player> fly = new ArrayList<Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player))
			return true;
		Player player = (Player) sender;
		if(!(player.hasPermission("fly.cmd"))) {
			player.sendMessage("§cDazu hast du keine Rechte!");
			return true;
		}
		if(args.length == 0) {
			if(!fly.contains(player)) {
				fly.add(player);
				player.setAllowFlight(true);
				player.setFlying(true);
				player.sendMessage("§aDu kannst nun fliegen!");
				return true;
			} else {
				fly.remove(player);
				player.setAllowFlight(false);
				player.setFlying(false);
				player.sendMessage("§cDu kannst nicht mehr fliegen!");
				return true;
			}
		} else {
			player.sendMessage("§cBitte verwende /fly");
		}
		return false;
	}

	@Override
	public void onEnable() {
		getCommand("fly").setExecutor(this);
	}

	@Override
	public void onDisable() {
	}
}