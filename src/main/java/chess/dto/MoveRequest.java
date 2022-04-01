package chess.dto;

import chess.constant.Command;
import chess.domain.board.position.Position;

public class MoveRequest implements Request {

    private final Command command;
    private final Position sourcePosition;
    private final Position targetPosition;

    public MoveRequest(Command command, Position sourcePosition, Position targetPosition) {
        this.command = command;
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }

    @Override
    public Command getCommand() {
        return command;
    }

    @Override
    public Position getSourcePosition() {
        return sourcePosition;
    }

    @Override
    public Position getTargetPosition() {
        return targetPosition;
    }
}
