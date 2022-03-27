package chess.domain.piece;

import chess.domain.position.Position;

public class King extends AbstractPiece {
    King(PieceColor pieceColor) {
        super(pieceColor, PieceScore.KING);
    }

    private static boolean useKingStrategy(Position from, Position to) {
        if (from.isFarFromMoreThanOne(to)) {
            return false;
        }
        return from.isOnDiagonal(to) || from.isSameXAxis(to) || from.isSameYAxis(to);
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        if (from == to) {
            return false;
        }

        return useKingStrategy(from, to);
    }
}
