package Commands;

import CommandBase.CommandContext;
import CommandBase.ICommand;
import net.dv8tion.jda.api.JDA;

public class PingCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();
        jda.getRestPing().queue((ping) -> ctx.getChannel()
                .sendMessageFormat("Pong\n\nReset ping: %sms\nWS ping: %sms", ping, jda.getGatewayPing())
                .submit());
    }

    @Override
    public String getName() {
        return "ping";
    }
}

