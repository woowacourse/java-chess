package chess.command;

import chess.domain.board.Position;

public class Move extends BiCommand<Position> {

    protected Move(Position sourcePosition, Position targetPosition) {
        super(Type.MOVE, sourcePosition, targetPosition);
    }

    public Position getSourcePosition() {
        return super.getFirstArgument();
    }

    public Position getTargetPosition() {
        return super.getSecondArgument();
    }
}
