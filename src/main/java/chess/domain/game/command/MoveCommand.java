package chess.domain.game.command;

import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class MoveCommand implements Command {

    private static final Pattern POSITION = Pattern.compile("[a-h][1-8]");
    private final Consumer<List<String>> action;

    public MoveCommand(Consumer<List<String>> action) {
        this.action = action;
    }

    @Override
    public boolean isMatch(List<String> commands) {
        if (commands.size() != 3) {
            return false;
        }
        if (!POSITION.matcher(commands.get(1)).matches()) {
            return false;
        }
        if (!POSITION.matcher(commands.get(2)).matches()) {
            return false;
        }
        return "move".equalsIgnoreCase(commands.get(0));
    }

    @Override
    public void execute(List<String> commands) {
        action.accept(commands);
    }
}
