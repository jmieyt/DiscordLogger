package de.fontrendrendererobj.discordlogger.discordbot;

import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;

import de.fontrendrendererobj.discordlogger.DiscordLogger;
import de.fontrendrendererobj.discordlogger.util.DiscordWebhookUtil;

public class DiscordWebhook {

	private final String webhook;
	
	public DiscordWebhook(String webhook) {
		this.webhook = webhook;
	}
	public void sendMessage(String message) {
		FileConfiguration config = DiscordLogger.getInstance().getConfig();
		DiscordWebhookUtil webhook = new DiscordWebhookUtil(this.getToken());
	    webhook.setContent(message);
	    if(!config.getString("webhook-avatarUrl").equalsIgnoreCase("avatarUrl")) {
	    	webhook.setAvatarUrl(config.getString("webhook-avatarUrl"));
	    }
	    if(!config.getString("webhook-name").equalsIgnoreCase("name")) {
	    	webhook.setUsername(config.getString("webhook-name"));
	    }
	    webhook.setTts(config.getBoolean("webhook-tts"));
	    try {
			webhook.execute();
		} catch (IOException e) {
			System.err.println("ERROR while sending webhook " + e.getMessage());
		}
	}
	
	public String getToken() {
		return webhook;
	}
}
