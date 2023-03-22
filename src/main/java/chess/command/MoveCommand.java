package chess.command;

import chess.action.Action;
import chess.domain.position.Position;
import java.util.List;

public class MoveCommand implements Command {
    
    public static final int MOVE_ARGUMENTS_SIZE = 2;
    private static final String INVALID_ARGUMENT_COUNT_ERROR_MESSAGE = "move 명령어는 인자를 2개만 가질 수 있습니다.";
    private final Position from;
    private final Position to;
    
    public MoveCommand(final List<String> arguments) {
        this.validate(arguments);
        this.from = Position.from(arguments.get(0));
        this.to = Position.from(arguments.get(1));
    }
    
    private void validate(final List<String> arguments) {
        if (arguments.size() != MOVE_ARGUMENTS_SIZE) {
            throw new IllegalArgumentException(COMMAND_ERROR_PREFIX + INVALID_ARGUMENT_COUNT_ERROR_MESSAGE);
        }
    }
    
    @Override
    public void execute(final Action action) {
        action.move(this.from, this.to);
    }
    
    @Override
    public boolean isNotEnd() {
        return true;
    }
    
    public Position getTo() {
        return this.to;
    }
    
    public Position getFrom() {
        return this.from;
    }
}
