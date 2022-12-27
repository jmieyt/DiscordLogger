package de.fontrendrendererobj.discordlogger;

import de.fontrendrendererobj.discordlogger.utils.Pair;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.function.Consumer;

public final class EventCollectiveHandler {

    public EventCollectiveHandler(DiscordLoggerProperty property) {
        register(PlayerJoinEvent.class, event -> {
            if (property.isJoinEnable()) {
                publishWebhook("JoinEvent-message", event.getPlayer());
            }
        });
        register(PlayerQuitEvent.class, event -> {
            if (property.isQuitEnable()) {
                publishWebhook("QuitEvent-message", event.getPlayer());
            }
        });
        register(AsyncPlayerChatEvent.class, event -> {
            if (property.isChatEnable()) {
                publishWebhook("ChatEvent-message", event.getPlayer(), new Pair<>("message", event.getMessage()));
            }
        });
        register(BlockPlaceEvent.class, event -> {
            if (property.isPlaceBlockEnable()) {
                publishWebhook("BlockPlaceEvent-message", event.getPlayer(), new Pair<>("blockname", event.getBlock().getType().name()));
            }
        });
        register(BlockBreakEvent.class, event -> {
            if (property.isBreakBlockEnable()) {
                publishWebhook("BlockBreakEvent-message", event.getPlayer(), new Pair<>("blockname", event.getBlock().getType().name()));
            }
        });
    }

    private <T extends Event> void register(Class<T> clazz, Consumer<T> event) {
        Bukkit.getPluginManager().registerEvent(clazz, new Listener() {
        }, EventPriority.NORMAL, (listener1, event1) -> event.accept((T) event1), DiscordLogger.getInstance(), false);
    }

    private void publishWebhook(String channel, Player player, Pair... placeholders) {
        var message = DiscordLogger.getInstance().getConfig().getString(channel).replace("%playername%", player.getName());
        for (var placeholder : placeholders) {
            message = message.replace("%" + placeholder.getL().toString() + "%", placeholder.getR().toString());
        }
        DiscordLogger.getInstance().getDiscordWebhook().sendMessage(message);
    }
}
