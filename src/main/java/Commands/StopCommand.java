package Commands;

import CommandBase.CommandContext;
import CommandBase.ICommand;
import LavaPlayer.GuildMusicManager;
import LavaPlayer.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class StopCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) {
        GuildMessageReceivedEvent event = ctx.getEvent();
        TextChannel channel = event.getChannel();

        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();
        if(!selfVoiceState.inVoiceChannel()){
            channel.sendMessage("Я должна быть в голосовом канале для совершения этого действия." +
                    "Воспользуйтесь командой join").submit();
        }

        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();
        if(!memberVoiceState.inVoiceChannel()){
            channel.sendMessage("Вы должны быть в голосовом канале для совершения этого действия.").submit();
        }
        if(!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())){
            channel.sendMessage("Вы должны быть со мной в одном и том же голосовом канале для совершения этого действия")
                    .submit();
        }
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(ctx.getGuild());
        musicManager.scheduler.player.stopTrack();
        musicManager.scheduler.queue.clear();
        channel.sendMessage("Воспроизведение остановлено. Очередь очищена.")
                .submit();
    }

    @Override
    public String getName() {
        return "stop";
    }
}
