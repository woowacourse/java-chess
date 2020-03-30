package chess.domain.strategy;

import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlackPawnMoveStrategy extends PawnMoveStrategy {
    @Override
    public List<Direction> getDirections() {
        return new ArrayList<>(
                Arrays.asList(
                        Direction.SOUTH,
                        Direction.SOUTH_EAST,
                        Direction.SOUTH_WEST
                )
        );
    }

    @Override
    public int getInitialPawnRow() {
        return 7;
    }

    @Override
    protected Direction getForwardDirection() {
        return Direction.SOUTH;
    }
}
