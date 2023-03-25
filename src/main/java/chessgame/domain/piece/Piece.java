package chessgame.domain.piece;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piecetype.PieceType;

import java.util.Optional;

public abstract class Piece {

    private boolean isFirstMove;

    public Piece() {
        isFirstMove = true;
    }

    public abstract Optional<PieceType> piece();

    public abstract boolean isSameCamp(final Camp camp);

    public abstract boolean isExist();

    public abstract boolean isMovable(final Coordinate startCoordinate, final Coordinate endCoordinate);

    public abstract boolean isCatchable(final Optional<Camp> otherCamp,
                                        final Coordinate startCoordinate,
                                        final Coordinate endCoordinate);

    public abstract boolean canReap();

    public abstract Optional<Camp> camp();

    public abstract double score();

    protected boolean isFirstMove() {
        return isFirstMove;
    }

    public void checkMoved() {
        isFirstMove = false;
    }
}
