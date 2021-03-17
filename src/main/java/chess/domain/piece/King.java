package chess.domain.piece;

import chess.domain.location.Location;

public class King extends Piece {

    public King(final Location location) {
        super(location);
    }

    public static King of(final Location location) {
        return new King(location);
    }

    @Override
    public boolean isMovable(final Location target) {
        return location.isAdjacent(target);
    }
}
