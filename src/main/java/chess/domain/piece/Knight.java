package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.direction.KnightDirection;

public class Knight extends AbstractPiece {
    public Knight(PieceColor pieceColor) {
        super(pieceColor, PieceType.KNIGHT);
    }

    private static boolean useKnightStrategy(Position from, Position to) {
        return KnightDirection.isOnSevenShape(from, to);
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
