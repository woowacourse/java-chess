package chess.domain.pieces;

import chess.domain.position.Position;

public class Knight implements Type {

    private static final int FIRST_MOVEMENT_LIMIT = 2;
    private static final int SECOND_MOVEMENT_LIMIT = 1;

    @Override
    public String getSymbol() {
        return "N";
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        int columnGap = source.columnGap(target);
        int rowGap = source.rowGap(target);
        return moveVertical(columnGap, rowGap) || moveHorizontal(columnGap, rowGap);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    private boolean moveHorizontal(int columnGap, int rowGap) {
        return rowGap == FIRST_MOVEMENT_LIMIT && columnGap == SECOND_MOVEMENT_LIMIT;
    }

    private boolean moveVertical(int columnGap, int rowGap) {
        return columnGap == FIRST_MOVEMENT_LIMIT && rowGap == SECOND_MOVEMENT_LIMIT;
    }
}
