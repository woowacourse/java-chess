package chess.domain.pieces;

import chess.domain.position.Position;

public final class Pawn implements Type {

    public static final int VERTICAL_MOVEMENT_LIMIT = 1;
    public static final int HORIZONTAL_MOVEMENT_LIMIT = 0;

    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        int rowGap = source.rowGap(target);
        int columnGap = source.columnGap(target);
        return rowGap == VERTICAL_MOVEMENT_LIMIT && columnGap == HORIZONTAL_MOVEMENT_LIMIT;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
