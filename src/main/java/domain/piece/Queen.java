package domain.piece;

import domain.Color;
import domain.Location;

public class Queen extends Piece {

    public Queen(final Color color) {
        super(color);
    }

    @Override
    boolean movable(final Location start, final Location end) {
        return start.isSameLine(end) || start.isDiagonal(end);
    }
}
