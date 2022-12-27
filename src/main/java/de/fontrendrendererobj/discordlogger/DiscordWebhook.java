package de.fontrendrendererobj.discordlogger;

import java.io.IOException;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

import de.fontrendrendererobj.discordlogger.utils.DiscordWebhookUtil;

public record DiscordWebhook(@Getter String webhook) {

	public void sendMessage(String message) {
		FileConfiguration config = DiscordLogger.getInstance().getConfig();
		DiscordWebhookUtil webhook = new DiscordWebhookUtil(this.webhook);
		webhook.setContent(message);

		if (!config.getString("webhook-avatarUrl").equalsIgnoreCase("avatarUrl")) {
			webhook.setAvatarUrl(config.getString("webhook-avatarUrl"));
		}

		if (!config.getString("webhook-name").equalsIgnoreCase("name")) {
			webhook.setUsername(config.getString("webhook-name"));
		}

		webhook.setTts(config.getBoolean("webhook-tts"));
		try {
			webhook.execute();
		} catch (IOException e) {
			System.err.println("ERROR while sending webhook " + e.getMessage());
		}
	}
}
