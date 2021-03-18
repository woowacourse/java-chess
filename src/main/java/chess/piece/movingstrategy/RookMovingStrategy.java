package chess.piece.movingstrategy;

import chess.board.Point;
import chess.piece.Vector;
import java.util.List;

public class RookMovingStrategy implements MovingStrategy {
    static final int LENGTH = 7;

    private final List<Vector> rooksVector = Vector.axisVectors();

    @Override
    public Vector findMovableVector(Point source, Point destination) {
        int x = destination.minusX(source);
        int y = destination.minusY(source);

        return rooksVector.stream()
            .filter(vector -> vector.isSameDirection(x, y))
            .findFirst()
            .orElse(null);
    }

    @Override
    public int getMoveLength() {
        return LENGTH;
    }
}
