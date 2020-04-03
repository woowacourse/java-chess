package chess.domain.strategy;

import chess.domain.util.Direction;

import java.util.List;

public class WhitePawnMoveStrategy extends PawnMoveStrategy {
    @Override
    public List<Direction> getDirections() {
        return Direction.getDirectionsOfWhitePawn();
    }
}
