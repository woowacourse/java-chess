package chess.domain.piece;

import chess.domain.Color;
import java.util.List;

public final class Bishop extends Piece {
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean movable(List<Integer> distances, Piece target) {
        boolean isMovableDistance = Math.abs(distances.get(FILE_INDEX)) == (Math.abs(distances.get(RANK_INDEX)));
        boolean isOpponent = isOpponent(target);

        return isMovableDistance && isOpponent;
    }

    @Override
    public boolean isBishop() {
        return true;
    }
}
