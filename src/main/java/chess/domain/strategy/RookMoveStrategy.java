package chess.domain.strategy;

import chess.domain.util.Direction;

import java.util.List;

public class RookMoveStrategy extends MultipleMoveStrategy {
    @Override
    public List<Direction> getDirections() {
        return Direction.getDirectionsOfRook();
    }
}
