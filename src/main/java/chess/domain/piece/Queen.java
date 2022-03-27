package chess.domain.piece;

import chess.domain.position.Position;

public class Queen extends AbstractPiece {
    Queen(PieceColor pieceColor) {
        super(pieceColor, PieceScore.QUEEN);
    }

    private static boolean useQueenStrategy(Position from, Position to) {
        return from.isOnDiagonal(to) || from.isSameXAxis(to) || from.isSameYAxis(to);
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        if (from == to) {
            return false;
        }

        return useQueenStrategy(from, to);
    }
}
