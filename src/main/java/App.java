import CommandBase.Listener;
import Properties.PropAnalizer;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class App {
    public static void main(String[] args) throws Exception{
        PropAnalizer props = new PropAnalizer();
        String token = (String) props.get("api.token");
        JDA jda = JDABuilder.createDefault(token)
                .addEventListeners(new Listener())
                .setActivity(Activity.watching("аниме свое опять."))
                .build();

    }
}
