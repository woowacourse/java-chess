package chess.domain.piece.movementStrategy;

import chess.domain.piece.MoveVector;
import java.util.List;

public class RookMovementStrategy extends AbstractMovementStrategy {

    static final int LENGTH = 7;

    @Override
    protected List<MoveVector> possibleMoveVectors() {
        return MoveVector.axisVectors();
    }

    @Override
    public int movementRange() {
        return LENGTH;
    }
}
