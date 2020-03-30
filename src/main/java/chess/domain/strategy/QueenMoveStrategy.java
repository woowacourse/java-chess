package chess.domain.strategy;

import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueenMoveStrategy extends MultipleMoveStrategy {
    @Override
    public List<Direction> getDirections() {
        return new ArrayList<>(
                Arrays.asList(
                        Direction.NORTH,
                        Direction.NORTH_EAST,
                        Direction.EAST,
                        Direction.SOUTH_EAST,
                        Direction.SOUTH,
                        Direction.SOUTH_WEST,
                        Direction.WEST,
                        Direction.NORTH_WEST
                )
        );
    }
}
