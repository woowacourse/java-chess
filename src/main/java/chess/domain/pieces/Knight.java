package chess.domain.pieces;

import chess.domain.position.Position;

public class Knight implements Type {

    private static final int FIRST_GAP = 2;
    private static final int SECOND_GAP = 1;

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

    private boolean moveHorizontal(int columnGap, int rowGap) {
        return rowGap == FIRST_GAP && columnGap == SECOND_GAP;
    }

    private boolean moveVertical(int columnGap, int rowGap) {
        return columnGap == FIRST_GAP && rowGap == SECOND_GAP;
    }
}
