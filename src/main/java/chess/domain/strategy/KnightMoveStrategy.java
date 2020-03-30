package chess.domain.strategy;

import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnightMoveStrategy extends SingleMoveStrategy {
    @Override
    public List<Direction> getDirections() {
        return new ArrayList<>(
                Arrays.asList(
                        Direction.NORTH_NORTH_EAST,
                        Direction.EAST_EAST_NORTH,
                        Direction.EAST_EAST_SOUTH,
                        Direction.SOUTH_SOUTH_EAST,
                        Direction.SOUTH_SOUTH_WEST,
                        Direction.WEST_WEST_SOUTH,
                        Direction.WEST_WEST_NORTH,
                        Direction.NORTH_NORTH_WEST
                )
        );
    }
}
