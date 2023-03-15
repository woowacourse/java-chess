package domain.piece;

import domain.Color;
import domain.Location;

public class Bishop extends Piece {

    private Bishop(final Color color) {
        super(color);
    }

    public static Bishop makeBlack() {
        return new Bishop(Color.BLACK);
    }

    public static Bishop makeWhite() {
        return new Bishop(Color.WHITE);
    }

    @Override
    public boolean movable(final Location start, final Location end) {
        return start.isDiagonal(end);
    }
}
