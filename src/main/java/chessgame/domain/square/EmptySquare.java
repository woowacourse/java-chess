package chessgame.domain.square;

import chessgame.domain.piece.Coordinate;
import chessgame.domain.piece.Piece;

public class EmptySquare extends Square {

    @Override
    public boolean canReap() {
        return false;
    }

    @Override
    public boolean isMovable(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return false;
    }

    @Override
    public boolean isExist() {
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
