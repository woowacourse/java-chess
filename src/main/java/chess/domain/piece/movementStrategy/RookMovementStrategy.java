package chess.domain.piece.movementStrategy;

import chess.domain.board.Point;
import chess.domain.piece.MoveVector;
import java.util.List;

public class RookMovementStrategy implements MovementStrategy {

    static final int LENGTH = 7;

    private final List<MoveVector> ROOKS_MOVE_VECTORS = MoveVector.axisVectors();

    @Override
    public MoveVector movableVector(Point source, Point destination) {
        int x = destination.xDifference(source);
        int y = destination.yDifference(source);

        return ROOKS_MOVE_VECTORS.stream()
            .filter(vector -> vector.isSameDirection(x, y))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("이동할 수 있는 방향이 아닙니다."));
    }

    @Override
    public boolean hasMovableVector(Point source, Point destination) {
        int x = destination.xDifference(source);
        int y = destination.yDifference(source);

        return ROOKS_MOVE_VECTORS.stream()
            .anyMatch(moveVector -> moveVector.isSameDirection(x, y));
    }

    @Override
    public int movementRange() {
        return LENGTH;
    }
}
