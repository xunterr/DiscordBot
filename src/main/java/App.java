import MessageHandlers.VoiceMessageHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class App {
    public static void main(String[] args) throws Exception{
        String token = "ODc5NjQ0NjUzMDMxNDAzNTIw.YSSu7A.dz0XI_2tlJYUvz0yMXfYPH9u8Ic";
        JDA jda = JDABuilder.createDefault(token)
                .addEventListeners(new VoiceMessageHandler())
                .setActivity(Activity.watching("аниме свое опять."))
                .build();

    }
}
