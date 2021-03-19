package chess.domain.piece.movingstrategy;

import chess.domain.board.Point;
import chess.domain.piece.MoveVector;
import java.util.List;

public class KingMovingStrategy implements MovingStrategy {

    private static final int LENGTH = 1;

    private final List<MoveVector> kingsMoveVector = MoveVector.everyVectors();

    @Override
    public MoveVector findMovableVector(Point source, Point destination) {
        int x = destination.minusX(source);
        int y = destination.minusY(source);

        return kingsMoveVector.stream()
            .filter(vector -> vector.isSameDirection(x, y))
            .findFirst()
            .orElse(null);
    }

    @Override
    public int getMoveLength() {
        return LENGTH;
    }
}
