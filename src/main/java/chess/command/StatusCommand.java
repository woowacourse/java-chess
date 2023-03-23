package chess.command;

import chess.domain.game.ActionHandler;
import chess.domain.game.Status;
import java.util.List;

public class StatusCommand implements Command {
    
    public static final int STATUS_ARGUMENTS_SIZE = 0;
    
    private final CommandType type = CommandType.STATUS;
    
    public StatusCommand(final List<String> arguments) {
        this.validate(arguments);
    }
    
    private void validate(final List<String> arguments) {
        if (arguments.size() != STATUS_ARGUMENTS_SIZE) {
            throw new IllegalArgumentException(
                    COMMAND_ERROR_PREFIX + this.type + INVALID_ARGUMENT_ERROR_MESSAGE);
        }
    }
    
    @Override
    public void update(ActionHandler action) {
        throw new UnsupportedOperationException(
                COMMAND_ERROR_PREFIX + this.type + INVALID_EXECUTE_ERROR_MESSAGE);
    }
    
    @Override
    public Status query(final ActionHandler action) {
        return action.status();
    }
    
    @Override
    public CommandType getType() {
        return this.type;
    }
    
}
