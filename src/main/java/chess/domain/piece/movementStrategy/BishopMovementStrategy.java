package chess.domain.piece.movementStrategy;

import chess.domain.piece.MoveVector;
import java.util.List;

public class BishopMovementStrategy extends AbstractMovementStrategy {

    private static final int LENGTH = 7;

    @Override
    protected List<MoveVector> possibleMoveVectors() {
        return MoveVector.diagonalVectors();
    }

    @Override
    public int movementRange() {
        return LENGTH;
    }
}
