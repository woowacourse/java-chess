package chess.domain.strategy;

import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RookMoveStrategy extends MultipleMoveStrategy {
    @Override
    public List<Direction> getDirections() {
        return new ArrayList<>(
                Arrays.asList(
                        Direction.NORTH,
                        Direction.EAST,
                        Direction.SOUTH,
                        Direction.WEST
                )
        );
    }
}
