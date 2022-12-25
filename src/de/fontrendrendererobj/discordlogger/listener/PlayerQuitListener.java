package de.fontrendrendererobj.discordlogger.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.fontrendrendererobj.discordlogger.DiscordLogger;

public class PlayerQuitListener implements Listener {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if(DiscordLogger.getInstance().getConfig().getBoolean("QuitEvent")) {
			DiscordLogger.getInstance().getDiscordWebhook().sendMessage(DiscordLogger.getInstance().getConfig().getString("QuitEvent-message").replace("%playername%", player.getName()));
		}
	}
}
