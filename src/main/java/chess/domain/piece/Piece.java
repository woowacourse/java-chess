package chess.domain.piece;

import chess.domain.location.Location;

public abstract class Piece implements Movable {

    protected Location location;

    public Piece(final Location location) {
        this.location = location;
    }

    public void move(final Location target) {
        if (isMovable(target)) {
            location = target;
        }
    }
}
