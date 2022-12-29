package de.fontrendrendererobj.discordlogger;

import lombok.Getter;
import net.http.aeon.annotations.Options;

@Getter
@Options(path = "plugins//DiscordLogger//", name = "config")
public class DiscordLoggerProperty {

    private String webhook = "ADDWEBHOOKLINK";
    private boolean isJoinEnable = true;
    private String joinMessage = "%playername% joined the game";

    private boolean isQuitEnable = true;
    private String quitMessage = "%playername% left the game";

    private boolean isBreakBlockEnable = true;
    private String breakMessage = "%playername% broke the block %blockname%";

    private boolean isPlaceBlockEnable = true;
    private String placeMessage = "%playername% placed the block %blockname%";

    private boolean isChatEnable = true;
    private String chatMessage = "%playername% placed the block %blockname%";

    private String prefix = "[DiscordLogger]";

}
