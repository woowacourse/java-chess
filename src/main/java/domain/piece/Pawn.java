package domain.piece;

import domain.type.Color;
import domain.type.PieceType;

public final class Pawn extends Piece {

    private Pawn(final Color color) {
        super(color, PieceType.PAWN, MoveCheckStrategy::isPawnMove);
    }

    public static Pawn makeBlack() {
        return new Pawn(Color.BLACK);
    }

    public static Pawn makeWhite() {
        return new Pawn(Color.WHITE);
    }
}
