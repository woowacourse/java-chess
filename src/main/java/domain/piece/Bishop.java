package domain.piece;

import domain.type.Color;
import domain.type.PieceType;

public final class Bishop extends Piece {

    private Bishop(final Color color) {
        super(color, PieceType.BISHOP, MoveCheckStrategy::isBishopMove);
    }

    public static Bishop makeBlack() {
        return new Bishop(Color.BLACK);
    }

    public static Bishop makeWhite() {
        return new Bishop(Color.WHITE);
    }
}
