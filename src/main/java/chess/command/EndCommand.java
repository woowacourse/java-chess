package chess.command;

import chess.domain.game.Action;
import chess.domain.game.Status;
import java.util.List;

public class EndCommand implements Command {
    
    public static final int END_ARGUMENTS_SIZE = 0;
    public static final String name = CommandType.END.name();
    
    
    public EndCommand(final List<String> arguments) {
        this.validate(arguments);
    }
    
    private void validate(final List<String> arguments) {
        if (arguments.size() != END_ARGUMENTS_SIZE) {
            throw new IllegalArgumentException(
                    COMMAND_ERROR_PREFIX + name + INVALID_ARGUMENT_ERROR_MESSAGE);
        }
    }
    
    @Override
    public void execute(final Action action) {
        action.end();
    }
    
    @Override
    public Status query(final Action action) {
        throw new UnsupportedOperationException(
                COMMAND_ERROR_PREFIX + name + INVALID_QUERY_ERROR_MESSAGE);
    }
    
    @Override
    public boolean isNotEnd() {
        return false;
    }
    
    @Override
    public boolean isStatus() {
        return false;
    }
}
