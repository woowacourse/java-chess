package chess.domain.strategy;

import chess.domain.util.Direction;

import java.util.List;

public class QueenMoveStrategy extends MultipleMoveStrategy {
    @Override
    public List<Direction> getDirections() {
        return Direction.getDirectionsOfQueen();
    }
}
