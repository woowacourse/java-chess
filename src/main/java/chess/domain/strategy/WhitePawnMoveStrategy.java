package chess.domain.strategy;

import chess.domain.util.Direction;

import java.util.List;

public class WhitePawnMoveStrategy extends PawnMoveStrategy {
    @Override
    public List<Direction> getDirections() {
        return Direction.getDirectionsOfWhitePawn();
    }

    @Override
    protected int getInitialPawnRow() {
        return 2;
    }

    @Override
    protected Direction getForwardDirection() {
        return Direction.NORTH;
    }
}
