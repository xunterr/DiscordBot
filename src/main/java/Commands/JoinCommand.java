package Commands;

import CommandBase.CommandContext;
import CommandBase.ICommand;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import javax.naming.event.EventContext;

public class JoinCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        GuildMessageReceivedEvent event = ctx.getEvent();
        VoiceChannel connectedChannel = event.getMember().getVoiceState().getChannel();
        AudioManager audioManager = event.getGuild().getAudioManager();
        audioManager.openAudioConnection(connectedChannel);
        event.getMessage().getChannel().sendMessageFormat("Голосовой канал: %s", connectedChannel.getName()).submit();
    }

    @Override
    public String getName() {
        return "join";
    }
}
