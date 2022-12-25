package de.fontrendrendererobj.discordlogger.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import de.fontrendrendererobj.discordlogger.DiscordLogger;

public class BlockPlaceListener implements Listener {
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if(DiscordLogger.getInstance().getConfig().getBoolean("BlockPlaceEvent")) {
			DiscordLogger.getInstance().getDiscordWebhook().sendMessage(DiscordLogger.getInstance().getConfig().getString("BlockPlaceEvent-message").replace("%playername%", player.getName()).replace("%blockname%", event.getBlock().getType().name()));
		}
	}
}
