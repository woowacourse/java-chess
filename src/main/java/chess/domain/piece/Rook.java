package chess.domain.piece;

import chess.domain.position.Position;

public class Rook extends AbstractPiece {
    Rook(PieceColor pieceColor) {
        super(pieceColor, PieceScore.ROOK);
    }

    private static boolean useRookStrategy(Position from, Position to) {
        return from.isSameXAxis(to) || from.isSameYAxis(to);
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        if (from == to) {
            return false;
        }

        return useRookStrategy(from, to);
    }
}