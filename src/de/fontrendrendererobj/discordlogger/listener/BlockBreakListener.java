package de.fontrendrendererobj.discordlogger.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import de.fontrendrendererobj.discordlogger.DiscordLogger;

public class BlockBreakListener implements Listener {
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if(DiscordLogger.getInstance().getConfig().getBoolean("BlockBreakEvent")) {
			DiscordLogger.getInstance().getDiscordWebhook().sendMessage(DiscordLogger.getInstance().getConfig().getString("BlockBreakEvent-message").replace("%playername%", player.getName()).replace("%blockname%", event.getBlock().getType().name()));
		}
	}
}
