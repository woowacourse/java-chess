package domain.piece;

import domain.Color;
import domain.Location;

public class Bishop extends Piece {

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public boolean movable(final Location start, final Location end) {
        return start.isDiagonal(end);
    }
}
