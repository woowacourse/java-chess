package chess.domain.piece.movingstrategy;

import chess.domain.board.Point;
import chess.domain.piece.MoveVector;
import java.util.List;

public class KingMovingStrategy implements MovingStrategy {

    private static final int LENGTH = 1;

    private static final List<MoveVector> KINGS_MOVE_VECTORS = MoveVector.everyVectors();

    @Override
    public MoveVector movableVector(Point source, Point destination) {
        int x = destination.xDifference(source);
        int y = destination.yDifference(source);

        return KINGS_MOVE_VECTORS.stream()
            .filter(vector -> vector.isSameDirection(x, y))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("이동할 수 있는 방향이 아닙니다."));
    }

    @Override
    public boolean hasMovableVector(Point source, Point destination) {
        int x = destination.xDifference(source);
        int y = destination.yDifference(source);

        return KINGS_MOVE_VECTORS.stream()
            .anyMatch(moveVector -> moveVector.isSameDirection(x, y));
    }

    @Override
    public int movingLength() {
        return LENGTH;
    }
}
