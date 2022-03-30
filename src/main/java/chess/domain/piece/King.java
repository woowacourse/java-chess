package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.direction.DiagonalDirection;

public class King extends AbstractPiece {
    public King(PieceColor pieceColor) {
        super(pieceColor, PieceType.KING);
    }

    private static boolean useKingStrategy(Position from, Position to) {
        if (from.isFarFromMoreThanOne(to)) {
            return false;
        }
        return DiagonalDirection.isOnDiagonal(from, to) || from.isSameXAxis(to) || from.isSameYAxis(to);
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        if (from == to) {
            return false;
        }

        return useKingStrategy(from, to);
    }
}
