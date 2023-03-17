package chessgame.domain.square;

import chessgame.domain.piece.Coordinate;
import chessgame.domain.piece.Piece;

public abstract class Square {

    private boolean isFirstMove;

    public Square() {
        isFirstMove = true;
    }

    public abstract Piece getPieceType();
    public abstract Camp getCamp();
    public abstract boolean isMovable(Coordinate startCoordinate, Coordinate endCoordinate);
    public abstract boolean isExist();
    public abstract boolean canReap();

    protected boolean isFirstMove() {
        return isFirstMove;
    }

    public void checkMoved() {
        isFirstMove = false;
    }
}
