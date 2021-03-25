package chess.domain.piece.movingstrategy;

import chess.domain.board.Point;
import chess.domain.board.Row;
import chess.domain.piece.Vector;
import java.util.List;

public class PawnMovingStrategy implements MovingStrategy {

    private static final int MOVABLE_LENGTH = 1;

    private final List<Vector> pawnsVector = Vector.pawnVectors();

    @Override
    public Vector findMovableVector(Point source, Point destination) {
        int x = destination.minusX(source);
        int y = destination.minusY(source);

        Vector possibleVector = findPawnsVectorByDifference(x, y);

        if (y == 2 && !source.isRow(Row.TWO)) {
            throw new IllegalArgumentException("폰이 움직일 수 있는 위치가 아닙니다.");
        }
        return possibleVector;
    }

    private Vector findPawnsVectorByDifference(int x, int y) {

        return pawnsVector.stream()
            .filter(vector -> vector.isSameDirection(x, y))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("폰이 움직일 수 있는 위치가 아닙니다."));
    }

    @Override
    public int getMoveLength() {
        return MOVABLE_LENGTH;
    }
}
