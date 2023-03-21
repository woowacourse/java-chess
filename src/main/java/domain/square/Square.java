package domain.square;

import domain.piece.Coordinate;
import domain.piece.Piece;

public abstract class Square implements Cloneable {

    private boolean isFirstMove;

    public Square() {
        isFirstMove = true;
    }

    public abstract Piece getPieceType();
    public abstract Camp getCamp();
    public abstract boolean isMovable(final Coordinate startCoordinate, final Coordinate endCoordinate);
    public abstract boolean isExist();
    public abstract boolean canReap();

    protected boolean isFirstMove() {
        return isFirstMove;
    }

    public void checkMoved() {
        isFirstMove = false;
    }

    @Override
    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
