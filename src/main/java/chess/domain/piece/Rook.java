package chess.domain.piece;

import chess.domain.move.Direction;

import java.util.Set;

import static chess.domain.move.Direction.*;

public final class Rook extends Piece {
    private static final Set<Direction> directions = Set.of(UP, DOWN, LEFT, RIGHT);

    public Rook(final boolean isWhite) {
        super(isWhite);
    }

    @Override
    public String name() {
        if (super.isWhite()) {
            return "r";
        }
        return "R";
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
