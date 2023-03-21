package domain.piece;

import domain.type.Color;
import domain.type.PieceType;

public final class Knight extends Piece {

    private Knight(final Color color) {
        super(color, PieceType.KNIGHT, MoveCheckStrategy::isKnightMove);
    }

    public static Knight makeBlack() {
        return new Knight(Color.BLACK);
    }

    public static Knight makeWhite() {
        return new Knight(Color.WHITE);
    }
}
