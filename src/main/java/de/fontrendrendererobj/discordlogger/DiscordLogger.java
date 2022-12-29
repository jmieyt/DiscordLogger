package de.fontrendrendererobj.discordlogger;

import lombok.Getter;
import net.http.aeon.Aeon;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

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

        File discordLoggerDirectory = new File("plugins//DiscordLogger");
        discordLoggerDirectory.mkdirs();

        this.property = Aeon.insert(new DiscordLoggerProperty());
        this.discordWebhook = new DiscordWebhook(getConfig().getString("webhook"));

        // Check if token is in Config File
        if (this.property.getWebhook().equalsIgnoreCase("ADDWEBHOOKLINK")) {
            Bukkit.getConsoleSender().sendMessage("[WARNING] " + prefix + "No Discord Webhook Link in Config-File! exiting...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        } else {
            Bukkit.getConsoleSender().sendMessage(prefix + "Discord-Webhook detected!");
        }

        // Loading Data
        this.prefix = this.property.getPrefix();
        new EventCollectiveHandler(this.property);

        Bukkit.getConsoleSender().sendMessage(this.prefix + "DiscordLogger made by fontrendererobj :)");
        Bukkit.getConsoleSender().sendMessage(this.prefix + "Bug? or you need more Events? write me on discord: fontrendererobj#6023 or create a GitHub issue.");
    }

    @Override
    public void onDisable() {
    }
}
