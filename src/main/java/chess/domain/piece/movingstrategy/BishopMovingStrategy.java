package chess.domain.piece.movingstrategy;

import chess.domain.board.Point;
import chess.domain.piece.MoveVector;
import java.util.List;

public class BishopMovingStrategy implements MovingStrategy {

    private static final int LENGTH = 7;

    private final List<MoveVector> bishopMoveVector = MoveVector.diagonalVectors();

    @Override
    public MoveVector findMovableVector(Point source, Point destination) {
        int x = destination.minusX(source);
        int y = destination.minusY(source);

        return bishopMoveVector.stream()
            .filter(moveVector -> moveVector.isSameDirection(x, y))
            .findFirst()
            .orElse(null);
    }

    @Override
    public int getMoveLength() {
        return LENGTH;
    }
}
