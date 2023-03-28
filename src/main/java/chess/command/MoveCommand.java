package chess.command;

import chess.domain.game.ActionHandler;
import chess.domain.game.Status;
import chess.domain.position.Position;
import chess.history.History;
import chess.history.Move;
import java.util.List;

public class MoveCommand implements Command {
    
    public static final int MOVE_ARGUMENTS_SIZE = 2;
    
    private final CommandType type = CommandType.MOVE;
    
    private final Move move;
    
    public MoveCommand(final List<String> arguments) {
        this.validate(arguments);
        final Position from = Position.from(arguments.get(0));
        final Position to = Position.from(arguments.get(1));
        this.move = Move.create(from, to);
    }
    
    private void validate(final List<String> arguments) {
        if (arguments.size() != MOVE_ARGUMENTS_SIZE) {
            throw new IllegalArgumentException(
                    COMMAND_ERROR_PREFIX + this.type + INVALID_ARGUMENT_COUNT_ERROR_MESSAGE);
        }
    }
    
    @Override
    public Status query(final ActionHandler action) {
        throw new UnsupportedOperationException(
                COMMAND_ERROR_PREFIX + this.type + INVALID_QUERY_ERROR_MESSAGE);
    }
    
    @Override
    public void update(final ActionHandler action) {
        action.move(this.move.getFrom(), this.move.getTo());
    }
    
    @Override
    public void addHistory(final History history) {
        history.add(this.move);
    }
    
    @Override
    public CommandType getType() {
        return this.type;
    }
    
    public Position getFrom() {
        return this.move.getFrom();
    }
    
    public Position getTo() {
        return this.move.getTo();
    }
}
