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
        boolean isMovableDistance = distances.get(FILE_INDEX).equals(distances.get(RANK_INDEX));
        boolean isOpponent = isOpponent(target);

        return isMovableDistance && isOpponent;
    }
}
