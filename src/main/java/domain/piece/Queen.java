package domain.piece;

import domain.type.Color;
import domain.type.PieceType;

public final class Queen extends Piece {

    private Queen(final Color color) {
        super(color, PieceType.QUEEN, MoveCheckStrategy::isQueenMove);
    }

    public static Queen makeBlack() {
        return new Queen(Color.BLACK);
    }

    public static Queen makeWhite() {
        return new Queen(Color.WHITE);
    }
}
