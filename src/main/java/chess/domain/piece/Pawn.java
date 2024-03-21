package chess.domain.piece;

import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Square;
import chess.domain.Rank;

public class Pawn extends Piece {

    public Pawn(PieceColor color) {
        super(PieceType.PAWN, color);
    }

    @Override
    public boolean canMove(Square source, Square target) {
        if (isBackward(source, target)) {
            return false;
        }
        if (isFirstStep(source)) {
            return source.calculateRankDiff(target.rank()) <= 2;
        }
        return source.calculateRankDiff(target.rank()) == 1;
    }

    private boolean isBackward(Square source, Square target) {
        if (getColor() == PieceColor.BLACK) {
            return source.rank().get() < target.rank().get();
        }
        return source.rank().get() > target.rank().get();
    }

    private boolean isFirstStep(Square square) {
        if (getColor() == PieceColor.BLACK) {
            return square.rank() == Rank.SEVEN;
        }
        return square.rank() == Rank.TWO;
    }
}
