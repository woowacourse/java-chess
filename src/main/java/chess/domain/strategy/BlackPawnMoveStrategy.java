package chess.domain.strategy;

import chess.domain.util.Direction;

import java.util.List;

public class BlackPawnMoveStrategy extends PawnMoveStrategy {
    @Override
    public List<Direction> getDirections() {
        return Direction.getDirectionsOfBlackPawn();
    }
}
