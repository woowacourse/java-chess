package chess.domain.strategy;

import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WhitePawnMoveStrategy extends PawnMoveStrategy {
    @Override
    public List<Direction> getDirections() {
        return new ArrayList<>(
                Arrays.asList(
                        Direction.NORTH,
                        Direction.NORTH_EAST,
                        Direction.NORTH_WEST
                )
        );
    }

    @Override
    protected int getInitialPawnRow() {
        return 2;
    }

    @Override
    protected Direction getForwardDirection() {
        return Direction.NORTH;
    }
}
