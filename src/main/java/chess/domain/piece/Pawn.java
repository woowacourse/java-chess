package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.square.Square;
import chess.domain.square.Rank;

public class Pawn extends Piece {
    private static final int FIRST_STEP_LIMIT = 2;
    private static final int STEP_LIMIT = 1;

    public Pawn(PieceColor color, Square square) {
        super(color, square);
    }

    private boolean isBackward(Square source, Square target) {
        if (getColor() == PieceColor.BLACK) {
            return target.isLowerThan(source);
        }
        return target.isUpperThan(source);
    }

    private boolean isFirstStep(Square square) {
        if (getColor() == PieceColor.BLACK) {
            return square.isSameRank(Rank.SEVEN);
        }
        return square.isSameRank(Rank.TWO);
    }

    @Override
    public void move(Board board, Square target) {

    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }
}
