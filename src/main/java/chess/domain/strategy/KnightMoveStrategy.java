package chess.domain.strategy;

import chess.domain.util.Direction;

import java.util.List;

public class KnightMoveStrategy extends SingleMoveStrategy {
    @Override
    public List<Direction> getDirections() {
        return Direction.getDirectionsOfKnight();
    }
}
