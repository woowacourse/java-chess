package chess.domain.piece;

import chess.domain.Color;
import java.util.List;

public final class King extends Piece {
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    public King(Color color) {
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
        return distances.get(FILE_INDEX).equals(distances.get(RANK_INDEX)) && distances.get(FILE_INDEX) == 1;
    }

    private boolean isHorizonOrVerticalMove(List<Integer> distances) {
        int file = distances.get(FILE_INDEX);
        int rank = distances.get(RANK_INDEX);

        return ((Math.abs(file) == 1) && rank == 0) || (file == 0 && (Math.abs(rank) == 1));
    }
}
