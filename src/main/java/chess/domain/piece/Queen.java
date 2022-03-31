package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.direction.DiagonalDirection;

public class Queen extends AbstractPiece {

    public Queen(PieceColor pieceColor) {
        super(pieceColor, PieceType.QUEEN);
    }

    private static boolean useQueenStrategy(Position from, Position to) {
        return DiagonalDirection.isOnDiagonal(from, to) || from.isSameXAxis(to) || from.isSameYAxis(to);
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        if (from == to) {
            return false;
        }

        return useQueenStrategy(from, to);
    }
}
