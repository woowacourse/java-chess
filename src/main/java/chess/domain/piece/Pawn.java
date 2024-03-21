package chess.domain.piece;

import chess.domain.square.Square;
import chess.domain.square.Rank;

public class Pawn extends Piece {

    private static final int FIRST_STEP_LIMIT = 2;
    private static final int STEP_LIMIT = 1;

    public Pawn(PieceColor color) {
        super(PieceType.PAWN, color);
    }

    @Override
    public boolean canMove(Square source, Square target) {
        if (isBackward(source, target)) {
            return false;
        }
        if (isFirstStep(source)) {
            return source.calculateRankDiff(target.rank()) <= FIRST_STEP_LIMIT;
        }
        return source.calculateRankDiff(target.rank()) == STEP_LIMIT;
    }

    private boolean isBackward(Square source, Square target) {
        if (getColor() == PieceColor.BLACK) {
            return source.isLessRankThan(target);
        }
        return source.isGreaterRankThan(target);
    }

    private boolean isFirstStep(Square square) {
        if (getColor() == PieceColor.BLACK) {
            return square.rank() == Rank.SEVEN;
        }
        return square.rank() == Rank.TWO;
    }
}
