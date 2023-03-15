package domain.piece;

import domain.Color;
import domain.Location;

public class Queen extends Piece {

    private Queen(final Color color) {
        super(color);
    }

    public static Queen makeBlack() {
        return new Queen(Color.BLACK);
    }

    public static Queen makeWhite() {
        return new Queen(Color.WHITE);
    }

    @Override
    boolean movable(final Location start, final Location end) {
        return start.isSameLine(end) || start.isDiagonal(end);
    }
}
