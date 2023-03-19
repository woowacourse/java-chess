package domain.piece;

import domain.type.Color;
import domain.type.PieceType;

public final class Rook extends Piece {

    private Rook(final Color color) {
        super(color, PieceType.ROOK, MoveCheckStrategy::isRookMove);
    }

    public static Rook makeBlack() {
        return new Rook(Color.BLACK);
    }

    public static Rook makeWhite() {
        return new Rook(Color.WHITE);
    }
}
