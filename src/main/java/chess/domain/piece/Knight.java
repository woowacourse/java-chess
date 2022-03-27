package chess.domain.piece;

import chess.domain.position.Position;

public class Knight extends AbstractPiece {
    Knight(PieceColor pieceColor) {
        super(pieceColor, PieceScore.KNIGHT);
    }

    private static boolean useKnightStrategy(Position from, Position to) {
        return from.isOnSevenShape(to);
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        if (from == to) {
            return false;
        }

        return useKnightStrategy(from, to);
    }

    @Override
    public boolean isAbleToJump() {
        return true;
    }
}
