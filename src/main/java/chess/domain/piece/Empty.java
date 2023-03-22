package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.color.Color;

public final class Empty extends Piece {

    public Empty(final Color color) {
        super(color);
    }

    @Override
    public String name() {
        return ".";
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
