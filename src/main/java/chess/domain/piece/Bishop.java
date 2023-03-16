package chess.domain.piece;

import chess.domain.move.Direction;

import java.util.Set;

import static chess.domain.move.Direction.*;

public final class Bishop extends Piece {

    private static final Set<Direction> directions = Set.of(LEFT_UP, LEFT_DOWN, RIGHT_DOWN, RIGHT_UP);

    public Bishop(final String name) {
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
