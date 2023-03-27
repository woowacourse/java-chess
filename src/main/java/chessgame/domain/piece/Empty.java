package chessgame.domain.piece;

import chessgame.domain.chessgame.Camp;
import chessgame.domain.coordinate.Coordinate;

public class Empty extends Piece {

    public Empty() {
        super(PieceType.EMPTY, Camp.EMPTY);
    }

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return false;
    }

    @Override
    public boolean isCatchable(final Camp otherCamp,
                               final Coordinate startCoordinate,
                               final Coordinate endCoordinate) {
        return false;
    }

    @Override
    public boolean canReap() {
        return false;
    }
}
