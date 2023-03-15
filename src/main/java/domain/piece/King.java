package domain.piece;

import domain.Color;
import domain.Location;

public class King extends Piece {

    private King(final Color color) {
        super(color);
    }

    public static King makeBlack() {
        return new King(Color.BLACK);
    }

    public static King makeWhite() {
        return new King(Color.WHITE);
    }

    @Override
    boolean movable(final Location start, final Location end) {
        if (start.isSameCol(end)) {
            return Math.abs(start.getRow() - end.getRow()) == 1;
        }
        if (start.isSameRow(end)) {
            return Math.abs(start.getCol() - end.getCol()) == 1;
        }
        if (start.isDiagonal(end)) {
            return Math.abs(start.getCol() - end.getCol()) == 1;
        }
        return false;
    }
}
