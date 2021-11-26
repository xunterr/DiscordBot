package MessageHandlers;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class VoiceMessageHandler extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Message message = event.getMessage();

        if(message.getContentDisplay().split(" ")[0].equalsIgnoreCase(".play")) {
            VoiceChannel connectedChannel = event.getMember().getVoiceState().getChannel();
            AudioManager audioManager = event.getGuild().getAudioManager();
            audioManager.openAudioConnection(connectedChannel);
            event.getMessage().getChannel().sendMessage("Оаомао пора слушать роцк!!!!").submit();

        }else if(message.getContentDisplay().split(" ")[0].equalsIgnoreCase(".leave")){
            VoiceChannel channel = event.getGuild().getSelfMember().getVoiceState().getChannel();
            event.getGuild().getAudioManager().closeAudioConnection();
            event.getMessage().getChannel().sendMessage("Блинб мама спать гонит(((").submit();
        }
    }
}
