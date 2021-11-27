package Commands;

import CommandBase.CommandContext;
import CommandBase.ICommand;
import LavaPlayer.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.net.URI;
import java.net.URISyntaxException;

public class PlayCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        GuildMessageReceivedEvent event = ctx.getEvent();
        TextChannel channel = event.getChannel();

        if(ctx.getArgs().isEmpty()){
            channel.sendMessage("Используйте %s play <ссылка youtube / поисковый запрос> (-loop)").submit();
            return;
        }

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

        String url = ctx.getArgs().get(0);

        if(!isUrl(url)){
            url = "ytsearch:" + url;
        }

        PlayerManager.getInstance().loadAndPlay(channel, url);
    }

    @Override
    public String getName() {
        return "play";
    }

    private boolean isUrl(String url){
        try{
            new URI(url);
            return true;
        }catch (URISyntaxException ex){
            return false;
        }
    }
}
