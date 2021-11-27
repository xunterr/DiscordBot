package CommandBase;

import Commands.*;
import Properties.PropAnalizer;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommandManager {
    private final List<ICommand> commands = new ArrayList<>();

    public CommandManager(){
        addCommand(new PingCommand());
        addCommand(new JoinCommand());
        addCommand(new LeaveCommand());
        addCommand(new PlayCommand());
        addCommand(new StopCommand());
        addCommand(new SkipCommand());
    }

    private void addCommand(ICommand cmd){
        boolean nameFound = commands.stream().anyMatch((it) -> it.getName().equals(cmd.getName()));
        if(!nameFound){
            commands.add(cmd);
        }
    }

    @Nullable
    private ICommand getCommand(String search){
        String searchLower = search.toLowerCase();
        for (ICommand cmd : this.commands) {
            if(cmd.getName().equals(searchLower) || cmd.getAliases().contains(searchLower)){
                return cmd;
            }
        }
        return null;
    }

    void handle(GuildMessageReceivedEvent event){
        PropAnalizer props = new PropAnalizer();
        String[] split = event.getMessage().getContentRaw()
                .replaceFirst("(?i)" + Pattern.quote((String) props.get("bot.prefix")), "")
                .split("\\s+");
        String invoke = split[0].toLowerCase();
        ICommand cmd = this.getCommand(invoke);
        if(cmd != null){
            event.getChannel().sendTyping().submit();
            List<String> args = Arrays.asList(split).subList(1, split.length);

            CommandContext ctx = new CommandContext(event, args);
            cmd.handle(ctx);
        }
    }
}
