package chess.domain.piece;

import chess.domain.location.Location;

public class Queen extends Piece {

    public Queen(final Location location) {
        super(location);
    }

    public static Queen of(Location location) {
        return new Queen(location);
    }

    @Override
    public boolean isMovable(final Location target) {
        return location.isHorizontalOrVertical(target) || location.isDiagonal(target);
    }
}
