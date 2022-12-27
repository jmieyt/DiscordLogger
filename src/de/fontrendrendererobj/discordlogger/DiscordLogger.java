package de.fontrendrendererobj.discordlogger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.fontrendrendererobj.discordlogger.discordbot.DiscordWebhook;
import de.fontrendrendererobj.discordlogger.listener.BlockBreakListener;
import de.fontrendrendererobj.discordlogger.listener.BlockPlaceListener;
import de.fontrendrendererobj.discordlogger.listener.PlayerChatListener;
import de.fontrendrendererobj.discordlogger.listener.PlayerJoinListener;
import de.fontrendrendererobj.discordlogger.listener.PlayerQuitListener;

public class DiscordLogger extends JavaPlugin {
	
	private static DiscordLogger instance;
	
	private String prefix = "[DiscordLogger] ";
	private DiscordWebhook discordWebhook;
	
	
	@Override
	public void onEnable() {
		this.instance = this;
		this.discordWebhook = new DiscordWebhook(getConfig().getString("webhook"));
		getConfig().addDefault("JoinEvent", true);
		getConfig().addDefault("JoinEvent-message", "%playername% joined the game");
		getConfig().addDefault("QuitEvent", true);
		getConfig().addDefault("QuitEvent-message", "%playername% left the game");
		getConfig().addDefault("BlockBreakEvent", true);
		getConfig().addDefault("BlockBreakEvent-message", "%playername% broke the block %blockname%");
		getConfig().addDefault("BlockPlaceEvent", true);
		getConfig().addDefault("BlockPlaceEvent-message", "%playername% placed the block %blockname%");
		getConfig().addDefault("ChatEvent", true);
		getConfig().addDefault("ChatEvent-message", "%playername%: %message%");
		getConfig().addDefault("webhook", "ADDWEBHOOKLINK");
		getConfig().addDefault("webhook-tts", true);
		getConfig().addDefault("webhook-name", "name");
		getConfig().addDefault("webhook-content", "content");
		getConfig().addDefault("webhook-avatarUrl", "avatarUrl");
		// SOON
//		getConfig().addDefault("webhook-embed", true);
//		getConfig().addDefault("webhook-embed-title", "Title");
//		getConfig().addDefault("webhook-embed-field", "Field1");
//		getConfig().addDefault("webhook-embed-footer", "footer");
//		getConfig().addDefault("webhook-embed-url", "url");
//		getConfig().addDefault("webhook-embed-color", Color.red);
		
		getConfig().addDefault("prefix", "[DiscordLogger]");
		getConfig().options().copyDefaults(true);
		saveConfig();

		// Check if token is in Config File
		if(getConfig().getString("webhook").equalsIgnoreCase("ADDWEBHOOKLINK")) {
			Bukkit.getConsoleSender().sendMessage("[WARNING] " + prefix + "No Discord Webhook Link in Config-File! exiting...");
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}else {
			Bukkit.getConsoleSender().sendMessage(prefix + "Discord-Webhook detected!");
		}

		// Loading Data
		prefix = getConfig().getString("prefix");
		Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), instance);
		Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), instance);
		Bukkit.getPluginManager().registerEvents(new PlayerChatListener(), instance);
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), instance);
		Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), instance);
        
		Bukkit.getConsoleSender().sendMessage(this.prefix + "DiscordLogger made by fontrendererobj :)");
		Bukkit.getConsoleSender().sendMessage(this.prefix + "Bug? or you need more Events? write me on discord: fontrendererobj#6023 or create a GitHub issue.");
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public static DiscordLogger getInstance() {
		return instance;
	}
	
	public DiscordWebhook getDiscordWebhook() {
		return discordWebhook;
	}
}
