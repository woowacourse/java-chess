package domain.piece;

import domain.Color;
import domain.Location;

public class Knight extends Piece {

    public Knight(final Color color) {
        super(color);
    }

    @Override
    boolean movable(final Location start, final Location end) {
        if (Math.abs(start.getCol() - end.getCol()) == 1) {
            return Math.abs(start.getRow() - end.getRow()) == 2;
        }
        if (Math.abs(start.getRow() - end.getRow()) == 1) {
            return Math.abs(start.getCol() - end.getCol()) == 2;
        }
        return false;
    }
}
