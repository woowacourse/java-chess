package chess.domain.piece.movementStrategy;

import chess.domain.piece.MoveVector;
import java.util.List;

public class QueenMovementStrategy extends AbstractMovementStrategy {

    private static final int MOVEMENT_RANGE = 7;

    @Override
    protected List<MoveVector> possibleMoveVectors() {
        return MoveVector.everyVectors();
    }

    @Override
    public int movementRange() {
        return MOVEMENT_RANGE;
    }
}
