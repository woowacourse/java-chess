package chess.command;

import chess.domain.game.Action;
import chess.domain.game.Status;
import chess.domain.position.Position;
import java.util.List;

public class MoveCommand implements Command {
    
    public static final int MOVE_ARGUMENTS_SIZE = 2;
    public static final String name = CommandType.MOVE.name();
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
                    COMMAND_ERROR_PREFIX + name + INVALID_ARGUMENT_COUNT_ERROR_MESSAGE);
        }
    }
    
    @Override
    public void execute(final Action action) {
        action.move(this.from, this.to);
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
    
    public Position getTo() {
        return this.to;
    }
    
    public Position getFrom() {
        return this.from;
    }
}
