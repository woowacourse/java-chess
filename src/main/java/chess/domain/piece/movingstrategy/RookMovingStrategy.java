package chess.domain.piece.movingstrategy;

import chess.domain.board.Point;
import chess.domain.piece.MoveVector;
import java.util.List;

public class RookMovingStrategy implements MovingStrategy {

    static final int LENGTH = 7;

    private final List<MoveVector> rooksMoveVector = MoveVector.axisVectors();

    @Override
    public MoveVector findMovableVector(Point source, Point destination) {
        int x = destination.minusX(source);
        int y = destination.minusY(source);

        return rooksMoveVector.stream()
            .filter(vector -> vector.isSameDirection(x, y))
            .findFirst()
            .orElse(null);
    }

    @Override
    public int getMoveLength() {
        return LENGTH;
    }
}
