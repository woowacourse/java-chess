package chess.piece.movingstrategy;

import chess.board.Point;
import chess.piece.Vector;
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
            .orElse(null);
    }

    @Override
    public int getMoveLength() {
        return LENGTH;
    }
}
