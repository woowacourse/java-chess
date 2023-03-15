package domain.piece;

import domain.piecetype.Coordinate;
import domain.piecetype.PieceType;

public class EmptyPiece implements Piece {

    @Override
    public Boolean isMovable(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return false;
    }

    @Override
    public PieceType getPieceType() {
        return null;
    }

    @Override
    public Camp getCamp() {
        return null;
    }
}
