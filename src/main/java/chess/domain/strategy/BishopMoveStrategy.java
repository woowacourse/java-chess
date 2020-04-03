package chess.domain.strategy;

import chess.domain.util.Direction;

import java.util.List;

public class BishopMoveStrategy extends MultipleMoveStrategy {
    @Override
    public List<Direction> getDirections() {
        return Direction.getDirectionsOfBishop();
    }
}
