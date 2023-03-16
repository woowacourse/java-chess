package chess.domain.piece;

import chess.domain.move.Direction;

import java.util.Set;

import static chess.domain.move.Direction.*;

public final class Queen extends Piece {
    private static final Set<Direction> directions = Set.of(UP, DOWN, LEFT, RIGHT, RIGHT_UP, RIGHT_DOWN, LEFT_UP, LEFT_DOWN);

    public Queen(final String name) {
        super(name);
    }

    @Override
    public boolean movable(final Direction direction) {
        return directions.contains(direction);
    }

    @Override
    public boolean movableByCount(final int count) {
        return true;
    }
}
