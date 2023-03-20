package chess.domain.game.command;

import java.util.List;

public class StartCommand implements Command {

    private final Runnable action;

    public StartCommand(Runnable action) {
        this.action = action;
    }

    @Override
    public boolean isMatch(List<String> commands) {
        if (commands.size() != 1) {
            return false;
        }
        return "start".equalsIgnoreCase(commands.get(0));
    }

    @Override
    public void execute(List<String> ignored) {
        action.run();
    }
}
