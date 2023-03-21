package chessgame.domain.square;

import chessgame.domain.piece.Coordinate;
import chessgame.domain.piece.Piece;

import java.util.Optional;

public abstract class Square {

    private boolean isFirstMove;

    public Square() {
        isFirstMove = true;
    }

    public abstract Optional<Piece> getPiece();

    public abstract boolean isSameCamp(Camp camp);

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
