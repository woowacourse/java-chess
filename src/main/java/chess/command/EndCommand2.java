package chess.command;

import chess.domain.game.ActionHandler2;
import chess.domain.game.Game2;
import chess.domain.game.Status;
import chess.history.History2;
import java.util.List;

public class EndCommand2 implements Command2 {
    
    public static final int END_ARGUMENTS_SIZE = 0;
    
    private final CommandType type = CommandType.END;
    
    public EndCommand2(final List<String> arguments) {
        this.validate(arguments);
    }
    
    private void validate(final List<String> arguments) {
        if (arguments.size() != END_ARGUMENTS_SIZE) {
            throw new IllegalArgumentException(
                    COMMAND_ERROR_PREFIX + this.type + INVALID_ARGUMENT_ERROR_MESSAGE);
        }
    }
    
    @Override
    public Status query(final ActionHandler2 action) {
        throw new UnsupportedOperationException(
                COMMAND_ERROR_PREFIX + this.type + INVALID_QUERY_ERROR_MESSAGE);
    }
    
    @Override
    public Game2 update(final ActionHandler2 action) {
        return action.end();
    }
    
    @Override
    public void addHistory(final History2 history) {
        throw new UnsupportedOperationException(
                COMMAND_ERROR_PREFIX + this.type + INVALID_EXECUTE_ERROR_MESSAGE);
        
    }
    
    @Override
    public CommandType getType() {
        return this.type;
    }
}
