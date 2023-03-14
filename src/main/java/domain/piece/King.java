package domain.piece;

import domain.Color;
import domain.Location;

public class King extends Piece {

    public King(final Color color) {
        super(color);
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
