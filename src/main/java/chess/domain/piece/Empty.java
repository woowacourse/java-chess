package chess.domain.piece;

import chess.domain.move.Direction;

public final class Empty extends Piece {

    public Empty(final String name) {
        super(name);
    }

    @Override
    public boolean movable(final Direction direction) {
        return false;
    }

    @Override
    public boolean movableByCount(final int count) {
        return false;
    }
}
