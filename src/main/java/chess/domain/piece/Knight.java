package chess.domain.piece;

import chess.domain.move.Direction;

import java.util.Set;

import static chess.domain.move.Direction.*;

public final class Knight extends Piece {

    private static final Set<Direction> directions = Set.of(KNIGHT_DOWN_LEFT, KNIGHT_DOWN_RIGHT, KNIGHT_UP_LEFT, KNIGHT_UP_RIGHT, KNIGHT_LEFT_UP, KNIGHT_LEFT_DOWN);

    public Knight(final boolean isWhite) {
        super(isWhite);
    }

    @Override
    public String name() {
        if (super.isWhite()) {
            return "n";
        }
        return "N";
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
