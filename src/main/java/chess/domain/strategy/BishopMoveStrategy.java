package chess.domain.strategy;

import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BishopMoveStrategy extends MultipleMoveStrategy {
    @Override
    public List<Direction> getDirections() {
        return new ArrayList<>(
                Arrays.asList(
                        Direction.NORTH_EAST,
                        Direction.SOUTH_EAST,
                        Direction.SOUTH_WEST,
                        Direction.NORTH_WEST
                )
        );
    }
}
