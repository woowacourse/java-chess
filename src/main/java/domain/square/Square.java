package domain.square;

import domain.piece.Coordinate;
import domain.piece.Piece;

public abstract class Square {

    private boolean isFirstMove;

    public Square() {
        isFirstMove = true;
    }

    public abstract Piece getPieceType();
    public abstract Camp getCamp();
    public abstract Boolean isMovable(final Coordinate startCoordinate, final Coordinate endCoordinate);
    public abstract Boolean isExist();
    public abstract Boolean canReap();

    protected Boolean isFirstMove() {
        return isFirstMove;
    }

    public void checkMoved() {
        isFirstMove = false;
    }
}
