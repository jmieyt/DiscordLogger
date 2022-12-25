package de.fontrendrendererobj.discordlogger.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import de.fontrendrendererobj.discordlogger.DiscordLogger;

public class PlayerChatListener implements Listener {
	
	@EventHandler
	public void onAsyncChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if(DiscordLogger.getInstance().getConfig().getBoolean("ChatEvent")) {
			DiscordLogger.getInstance().getDiscordWebhook().sendMessage(DiscordLogger.getInstance().getConfig().getString("ChatEvent-message").replace("%playername%", player.getName()).replace("%message%", event.getMessage()));
		}
	}
}
