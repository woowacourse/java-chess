package chessgame.domain.piece;

import chessgame.domain.piecetype.Coordinate;
import chessgame.domain.piecetype.PieceType;

import java.util.Optional;

public abstract class Piece {

    private boolean isFirstMove;

    public Piece() {
        isFirstMove = true;
    }

    public abstract Optional<PieceType> getPiece();

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
