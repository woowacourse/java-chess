package chess.domain.piece.movementStrategy;

import chess.domain.piece.MoveVector;
import java.util.List;

public class KnightMovementStrategy extends AbstractMovementStrategy {

    private static final int MOVEMENT_RANGE = 1;

    @Override
    protected List<MoveVector> possibleMoveVectors() {
        return MoveVector.knightVectors();
    }

    @Override
    public int movementRange() {
        return MOVEMENT_RANGE;
    }
}
