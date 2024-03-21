package chess.domain.piece;

import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.Rank;

public class Pawn extends Piece {

    public Pawn(PieceColor color) {
        super(PieceType.PAWN, color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        if (isBackward(source, target)) {
            return false;
        }
        if (isFirstPosition(source)) {
            return source.calculateRankDiff(target.rank()) <= 2;
        }
        return source.calculateRankDiff(target.rank()) == 1;
    }

    private boolean isBackward(Position source, Position target) {
        if (getColor() == PieceColor.BLACK) {
            return source.rank().get() < target.rank().get();
        }
        return source.rank().get() > target.rank().get();
    }

    private boolean isFirstPosition(Position position) {
        if (getColor() == PieceColor.BLACK) {
            return position.rank() == Rank.SEVEN;
        }
        return position.rank() == Rank.TWO;
    }
}
