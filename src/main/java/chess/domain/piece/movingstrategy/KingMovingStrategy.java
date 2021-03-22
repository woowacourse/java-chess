package chess.domain.piece.movingstrategy;

import chess.domain.board.Point;
import chess.domain.piece.Vector;
import java.util.List;

public class KingMovingStrategy implements MovingStrategy {

    private static final int LENGTH = 1;

    private final List<Vector> kingsVector = Vector.everyVectors();

    @Override
    public Vector findMovableVector(Point source, Point destination) {
        int x = destination.minusX(source);
        int y = destination.minusY(source);

        return kingsVector.stream()
            .filter(vector -> vector.isSameDirection(x, y))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("킹이 움직일 수 있는 위치가 아닙니다."));
    }

    @Override
    public int getMoveLength() {
        return LENGTH;
    }
}
