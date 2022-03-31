package chess.domain.piece;

import chess.domain.Color;
import java.util.List;

public final class Queen extends Piece {
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean movable(List<Integer> distances, Piece target) {
        return isRightMove(distances) && isOpponent(target);
    }

    private boolean isRightMove(List<Integer> distances) {
        return isDiagonalMove(distances) || isHorizonOrVerticalMove(distances);
    }

    private boolean isDiagonalMove(List<Integer> distances) {
        return Math.abs(distances.get(FILE_INDEX)) == Math.abs(distances.get(RANK_INDEX));
    }

    private boolean isHorizonOrVerticalMove(List<Integer> distances) {
        return distances.stream()
                .filter(integer -> integer == 0)
                .count() == 1;
    }
}
