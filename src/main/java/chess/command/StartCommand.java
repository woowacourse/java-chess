package chess.command;

import chess.domain.game.Action;
import chess.domain.game.Status;
import java.util.List;

public class StartCommand implements Command {
    
    public static final int START_ARGUMENTS_SIZE = 0;
    public static final String name = CommandType.START.name();
    
    public StartCommand(final List<String> arguments) {
        this.validate(arguments);
    }
    
    private void validate(final List<String> arguments) {
        if (arguments.size() != START_ARGUMENTS_SIZE) {
            throw new IllegalArgumentException(
                    COMMAND_ERROR_PREFIX + name + INVALID_ARGUMENT_ERROR_MESSAGE);
        }
    }
    
    @Override
    public void execute(final Action action) {
        action.start();
    }
    
    @Override
    public Status query(final Action action) {
        throw new UnsupportedOperationException(
                COMMAND_ERROR_PREFIX + name + INVALID_QUERY_ERROR_MESSAGE);
    }
    
    @Override
    public boolean isNotEnd() {
        return true;
    }
    
    @Override
    public boolean isStatus() {
        return false;
    }
}
