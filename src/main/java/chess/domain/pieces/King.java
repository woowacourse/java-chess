package chess.domain.pieces;

import chess.domain.position.Position;

public class King implements Role {

    public static final int MOVEMENT_LIMIT = 1;

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public boolean isMovable(Position source, Position target) {
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
}
