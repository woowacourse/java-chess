package chess.domain.piece;

import chess.domain.Side;

import java.util.List;

import static chess.domain.piece.Direction.*;

public class Knight extends MovablePiece {
    private static final List<Direction> POSSIBLE_DIRECTIONS = List.of(
            NORTH_NORTH_EAST, NORTH_EAST_EAST, NORTH_NORTH_WEST, NORTH_WEST_WEST,
            SOUTH_SOUTH_EAST, SOUTH_EAST_EAST, SOUTH_SOUTH_WEST, SOUTH_WEST_WEST);
    private static final int MAX_MOVE_DISTANCE = 1;

    public Knight(final Side side) {
        super(side);
    }

    @Override
    public boolean canMove(final Direction direction, final int distance) {
        return POSSIBLE_DIRECTIONS.contains(direction) && distance == MAX_MOVE_DISTANCE;
    }

    @Override
    public boolean canAttack(final Direction direction, final int distance, final MovablePiece target) {
        return canMove(direction, distance) && isOpponentSide(target);
    }
}
