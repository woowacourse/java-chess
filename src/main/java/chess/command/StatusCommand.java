package chess.command;

import chess.domain.game.Action;
import chess.domain.game.Status;
import java.util.List;

public class StatusCommand implements Command {
    
    public static final int STATUS_ARGUMENTS_SIZE = 0;
    public static final String name = CommandType.STATUS.name();
    
    public StatusCommand(final List<String> arguments) {
        this.validate(arguments);
    }
    
    private void validate(final List<String> arguments) {
        if (arguments.size() != STATUS_ARGUMENTS_SIZE) {
            throw new IllegalArgumentException(
                    COMMAND_ERROR_PREFIX + name + INVALID_ARGUMENT_ERROR_MESSAGE);
        }
    }
    
    @Override
    public void execute(Action action) {
        throw new UnsupportedOperationException(
                COMMAND_ERROR_PREFIX + name + INVALID_EXECUTE_ERROR_MESSAGE);
    }
    
    @Override
    public Status query(final Action action) {
        return action.status();
    }
    
    @Override
    public boolean isNotEnd() {
        return true;
    }
    
    @Override
    public boolean isStatus() {
        return true;
    }
    
}
