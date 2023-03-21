package domain.piece;

import domain.type.Color;
import domain.type.PieceType;

public final class King extends Piece {

    private King(final Color color) {
        super(color, PieceType.KING, MoveCheckStrategy::isKingMove);
    }

    public static King makeBlack() {
        return new King(Color.BLACK);
    }

    public static King makeWhite() {
        return new King(Color.WHITE);
    }
}
