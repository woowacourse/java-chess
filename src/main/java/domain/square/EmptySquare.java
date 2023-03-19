package domain.square;

import domain.piece.Coordinate;
import domain.piece.Piece;

public final class EmptySquare extends Square {

    @Override
    public Boolean canReap() {
        return false;
    }

    @Override
    public Boolean isMovable(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return false;
    }

    @Override
    public Boolean isExist() {
        return false;
    }

    @Override
    public Piece getPieceType() {
        return null;
    }

    @Override
    public Camp getCamp() {
        return null;
    }
}
