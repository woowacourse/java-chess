package chess.command;

import chess.domain.board.Position;

public class Move extends Command {
    private final Position sourcePosition;
    private final Position targetPosition;

    protected Move(Position sourcePosition, Position targetPosition) {
        super(Type.MOVE);
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }

    public Position getSourcePosition() {
        return sourcePosition;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }
}
