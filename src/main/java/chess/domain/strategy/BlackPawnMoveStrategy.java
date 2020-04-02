package chess.domain.strategy;

import chess.domain.util.Direction;

import java.util.List;

public class BlackPawnMoveStrategy extends PawnMoveStrategy {
    @Override
    public List<Direction> getDirections() {
        return Direction.getDirectionsOfBlackPawn();
    }

    @Override
    public int getInitialPawnRow() {
        return 7;
    }

    @Override
    protected Direction getForwardDirection() {
        return Direction.SOUTH;
    }
}
