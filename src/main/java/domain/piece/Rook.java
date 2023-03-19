package domain.piece;

import domain.Location;
import domain.type.Color;
import domain.type.PieceType;

public final class Rook extends Piece {

    private Rook(final Color color) {
        super(color, PieceType.ROOK);
    }

    public static Rook makeBlack() {
        return new Rook(Color.BLACK);
    }

    public static Rook makeWhite() {
        return new Rook(Color.WHITE);
    }

    @Override
    protected boolean isNotMovable(final Location start, final Location end) {
        return !start.isSameLine(end);
    }
}
