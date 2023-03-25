package chessgame.domain.piece;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.chessgame.Camp;

public class Empty extends Piece {

    public Empty() {
        super(PieceType.EMPTY, Camp.EMPTY, 0);
    }

    @Override
    public boolean isReachableByRule(Coordinate startCoordinate, Coordinate endCoordinate) {
        return false;
    }

    @Override
    public boolean isCatchable(Camp otherCamp, Coordinate startCoordinate, Coordinate endCoordinate) {
        return false;
    }

    @Override
    public boolean canReap() {
        return false;
    }
}
