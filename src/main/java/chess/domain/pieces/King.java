package chess.domain.pieces;

import chess.domain.position.Position;

public final class King implements Type {

    public static final int MOVEMENT_LIMIT = 1;

    @Override
    public boolean isMovable(final Position source, final Position target) {
        return source.columnGap(target) <= MOVEMENT_LIMIT && source.rowGap(target) <= MOVEMENT_LIMIT;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double score() {
        return 0;
    }

    @Override
    public String symbol() {
        return "K";
    }
}
