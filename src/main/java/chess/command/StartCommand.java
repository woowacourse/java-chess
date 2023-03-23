package chess.command;

import chess.domain.game.ActionHandler;
import chess.domain.game.Status;
import java.util.List;

public class StartCommand implements Command {
    
    public static final int START_ARGUMENTS_SIZE = 0;
    
    private final CommandType type = CommandType.START;
    
    public StartCommand(final List<String> arguments) {
        this.validate(arguments);
    }
    
    private void validate(final List<String> arguments) {
        if (arguments.size() != START_ARGUMENTS_SIZE) {
            throw new IllegalArgumentException(
                    COMMAND_ERROR_PREFIX + this.type + INVALID_ARGUMENT_ERROR_MESSAGE);
        }
    }
    
    @Override
    public void update(final ActionHandler action) {
        action.start();
    }
    
    @Override
    public Status query(final ActionHandler action) {
        throw new UnsupportedOperationException(
                COMMAND_ERROR_PREFIX + this.type + INVALID_QUERY_ERROR_MESSAGE);
    }
    
    @Override
    public CommandType getType() {
        return this.type;
    }
}
