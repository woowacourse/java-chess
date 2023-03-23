package chess.command;

import chess.domain.game.Action;
import chess.domain.game.Status;
import chess.domain.position.Position;
import java.util.List;

public class MoveCommand implements Command {
    
    public static final int MOVE_ARGUMENTS_SIZE = 2;
    
    private final CommandType type = CommandType.MOVE;
    private final Position from;
    private final Position to;
    
    public MoveCommand(final List<String> arguments) {
        this.validate(arguments);
        this.from = Position.from(arguments.get(0));
        this.to = Position.from(arguments.get(1));
    }
    
    private void validate(final List<String> arguments) {
        if (arguments.size() != MOVE_ARGUMENTS_SIZE) {
            throw new IllegalArgumentException(
                    COMMAND_ERROR_PREFIX + this.type + INVALID_ARGUMENT_COUNT_ERROR_MESSAGE);
        }
    }
    
    @Override
    public void update(final Action action) {
        action.move(this.from, this.to);
    }
    
    @Override
    public Status query(final Action action) {
        throw new UnsupportedOperationException(
                COMMAND_ERROR_PREFIX + this.type + INVALID_QUERY_ERROR_MESSAGE);
    }
    
    
    @Override
    public CommandType getType() {
        return this.type;
    }
    
    public Position getTo() {
        return this.to;
    }
    
    public Position getFrom() {
        return this.from;
    }
}
