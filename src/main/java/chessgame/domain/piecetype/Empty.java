package chessgame.domain.piecetype;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.Camp;

public class Empty extends PieceType {

    public Empty() {
        super(PieceTypeSymbol.EMPTY, Camp.EMPTY, 0);
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
