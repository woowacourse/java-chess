package chess.domain.piece.movementStrategy;

import chess.domain.piece.MoveVector;
import java.util.List;

public class PawnMovementStrategy extends AbstractMovementStrategy {

    private static final int LENGTH = 1;

    @Override
    protected List<MoveVector> possibleMoveVectors() {
        return MoveVector.pawnVectors();
    }

    @Override
    public int movementRange() {
        return LENGTH;
    }
}
