package de.fontrendrendererobj.discordlogger.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.fontrendrendererobj.discordlogger.DiscordLogger;

public class PlayerJoinListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(DiscordLogger.getInstance().getConfig().getBoolean("JoinEvent")) {
			DiscordLogger.getInstance().getDiscordWebhook().sendMessage(DiscordLogger.getInstance().getConfig().getString("JoinEvent-message").replace("%playername%", player.getName()));
		}
	}
}
