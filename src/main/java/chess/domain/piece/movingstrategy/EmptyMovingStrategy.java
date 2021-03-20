package chess.domain.piece.movingstrategy;

import chess.domain.board.Point;
import chess.domain.piece.MoveVector;

public class EmptyMovingStrategy implements MovingStrategy {

    @Override
    public MoveVector movableVector(Point source, Point destination) {
        throw new IllegalArgumentException("이동할 수 있는 방향이 아닙니다.");
    }

    @Override
    public boolean hasMovableVector(Point source, Point destination) {
        return false;
    }

    @Override
    public int movingLength() {
        return 0;
    }
}
