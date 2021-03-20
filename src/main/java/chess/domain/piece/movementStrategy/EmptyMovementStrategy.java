package chess.domain.piece.movementStrategy;

import chess.domain.board.Point;
import chess.domain.piece.MoveVector;

public class EmptyMovementStrategy implements MovementStrategy {

    @Override
    public MoveVector movableVector(Point source, Point destination) {
        throw new IllegalArgumentException("이동할 수 있는 방향이 아닙니다.");
    }

    @Override
    public boolean hasMovableVector(Point source, Point destination) {
        return false;
    }

    @Override
    public int movementRange() {
        return 0;
    }
}
