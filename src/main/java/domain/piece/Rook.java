package domain.piece;

import domain.Color;
import domain.Location;

public class Rook extends Piece {

    public Rook(final Color color) {
        super(color);
    }

    @Override
    boolean movable(final Location start, final Location end) {
        return start.isSameLine(end);
    }
}
