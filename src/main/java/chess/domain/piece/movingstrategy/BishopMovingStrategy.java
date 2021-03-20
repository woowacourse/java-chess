package chess.domain.piece.movingstrategy;

import chess.domain.board.Point;
import chess.domain.piece.MoveVector;
import java.util.List;

public class BishopMovingStrategy implements MovingStrategy {

    private static final int LENGTH = 7;

    private static final List<MoveVector> BISHOPS_MOVE_VECTORS = MoveVector.diagonalVectors();

    @Override
    public MoveVector movableVector(Point source, Point destination) {
        int x = destination.xDifference(source);
        int y = destination.yDifference(source);

        return BISHOPS_MOVE_VECTORS.stream()
            .filter(moveVector -> moveVector.isSameDirection(x, y))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("이동할 수 있는 방향이 아닙니다."));
    }

    @Override
    public boolean hasMovableVector(Point source, Point destination) {
        int x = destination.xDifference(source);
        int y = destination.yDifference(source);

        return BISHOPS_MOVE_VECTORS.stream()
            .anyMatch(moveVector -> moveVector.isSameDirection(x, y));
    }

    @Override
    public int movingLength() {
        return LENGTH;
    }
}
