package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.direction.DiagonalDirection;

public class Bishop extends AbstractPiece {
    public Bishop(PieceColor pieceColor) {
        super(pieceColor, PieceType.BISHOP);
    }

    private static boolean useBishopStrategy(Position from, Position to) {
        return DiagonalDirection.isOnDiagonal(from, to);
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        if (from == to) {
            return false;
        }

        return useBishopStrategy(from, to);
    }

}
