package de.fontrendrendererobj.discordlogger;

import lombok.Getter;
import net.http.aeon.Aeon;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class DiscordLogger extends JavaPlugin {

    @Getter
    private static DiscordLogger instance;
    private String prefix = "[DiscordLogger] ";

    private DiscordLoggerProperty property;
    private DiscordWebhook discordWebhook;

    @Override
    public void onEnable() {

        instance = this;

        this.discordWebhook = new DiscordWebhook(getConfig().getString("webhook"));
        this.property = Aeon.insert(new DiscordLoggerProperty());


        getConfig().addDefault("ChatEvent", true);
        getConfig().addDefault("ChatEvent-message", "%playername%: %message%");
        getConfig().addDefault("webhook", "ADDWEBHOOKLINK");
        getConfig().addDefault("webhook-tts", true);
        getConfig().addDefault("webhook-name", "name");
        getConfig().addDefault("webhook-content", "content");
        getConfig().addDefault("webhook-avatarUrl", "avatarUrl");

        getConfig().addDefault("prefix", "[DiscordLogger]");
        getConfig().options().copyDefaults(true);
        saveConfig();

        // Check if token is in Config File
        if (getConfig().getString("webhook").equalsIgnoreCase("ADDWEBHOOKLINK")) {
            Bukkit.getConsoleSender().sendMessage("[WARNING] " + prefix + "No Discord Webhook Link in Config-File! exiting...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        } else {
            Bukkit.getConsoleSender().sendMessage(prefix + "Discord-Webhook detected!");
        }

        // Loading Data
        prefix = getConfig().getString("prefix");
        new EventCollectiveHandler(this.property);

        Bukkit.getConsoleSender().sendMessage(this.prefix + "DiscordLogger made by fontrendererobj :)");
        Bukkit.getConsoleSender().sendMessage(this.prefix + "Bug? or you need more Events? write me on discord: fontrendererobj#6023 or create a GitHub issue.");
    }

    @Override
    public void onDisable() {
    }
}
