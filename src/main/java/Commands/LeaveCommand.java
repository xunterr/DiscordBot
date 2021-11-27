package Commands;

import CommandBase.CommandContext;
import CommandBase.ICommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class LeaveCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        GuildMessageReceivedEvent event = ctx.getEvent();
        VoiceChannel channel = event.getGuild().getSelfMember().getVoiceState().getChannel();
        event.getGuild().getAudioManager().closeAudioConnection();
        event.getChannel().sendMessageFormat("Покинут канал: %s", channel.getName()).submit();
    }

    @Override
    public String getName() {
        return "leave";
    }
}
