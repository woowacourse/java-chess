package chess.domain.piece.movingstrategy;

import chess.domain.board.Point;
import chess.domain.piece.Vector;
import java.util.List;

public class QueenMovingStrategy implements MovingStrategy {

    private static final int MOVABLE_LENGTH = 7;

    private final List<Vector> queensVector = Vector.everyVectors();

    @Override
    public Vector findMovableVector(Point source, Point destination) {
        int x = destination.minusX(source);
        int y = destination.minusY(source);

        return queensVector.stream()
            .filter(vector -> vector.isSameDirection(x, y))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("이 움직일 수 있는 위치가 아닙니다."));
    }

    @Override
    public int getMoveLength() {
        return MOVABLE_LENGTH;
    }
}
