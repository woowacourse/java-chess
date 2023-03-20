package chess.domain.game.command;

import java.util.List;

public class EndCommand implements Command {

    private final Runnable action;

    public EndCommand(Runnable action) {
        this.action = action;
    }

    @Override
    public boolean isMatch(List<String> commands) {
        if (commands.size() != 1) {
            return false;
        }
        return "end".equalsIgnoreCase(commands.get(0));
    }

    @Override
    public void execute(List<String> commands) {
        action.run();
    }
}
