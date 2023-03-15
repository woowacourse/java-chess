package domain.piece;

import domain.Color;
import domain.Location;

public class Rook extends Piece {

    private Rook(final Color color) {
        super(color);
    }

    public static Rook makeBlack() {
        return new Rook(Color.BLACK);
    }

    public static Rook makeWhite() {
        return new Rook(Color.WHITE);
    }

    @Override
    boolean movable(final Location start, final Location end) {
        return start.isSameLine(end);
    }
}
