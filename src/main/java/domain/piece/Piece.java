package domain.piece;

import domain.Color;
import domain.Location;

public abstract class Piece {

    protected final Color color;

    public Piece(final Color color) {
        this.color = color;
    }

    abstract boolean movable(Location start, Location end);
}
