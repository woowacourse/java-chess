package chess.domain.piece.movingstrategy;

import chess.domain.board.Point;
import chess.domain.board.Row;
import chess.domain.piece.Vector;
import java.util.List;

public class PawnMovingStrategy implements MovingStrategy {

    private static final int LENGTH = 2;

    private final List<Vector> pawnsVector = Vector.pawnVectors();

    @Override
    public Vector findMovableVector(Point source, Point destination) {
        int x = destination.minusX(source);
        int y = destination.minusY(source);

        Vector possibleVector = findPawnsVectorByDifference(x, y);

        if (y == 2 && !source.isRow(Row.TWO)) {
            return null;
        }
        return possibleVector;
    }

    private Vector findPawnsVectorByDifference(int x, int y) {

        return pawnsVector.stream()
            .filter(vector -> vector.isSameDirection(x, y))
            .findFirst()
            .orElse(null);
    }

    @Override
    public int getMoveLength() {
        return LENGTH;
    }
}
