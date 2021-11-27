package CommandBase;

import Properties.PropAnalizer;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Listener extends ListenerAdapter {
    private final CommandManager manager = new CommandManager();
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        Message message = event.getMessage();
        String raw = message.getContentRaw();
        if (event.getAuthor().isBot() || event.isWebhookMessage()) {
            return;
        }
        if (raw.startsWith(new PropAnalizer().get("bot.prefix"))) {
            manager.handle(event);
        }
    }
}
