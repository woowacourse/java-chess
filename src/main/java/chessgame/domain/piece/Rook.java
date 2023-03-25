package chessgame.domain.piece;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.coordinate.Inclination;
import chessgame.domain.chessgame.Camp;

import java.util.List;

import static chessgame.domain.coordinate.Inclination.*;

public class Rook extends Piece {

    private static final List<Inclination> availableInclinations = List.of(
            POSITIVE_INFINITY, NEGATIVE_INFINITY, ZERO, MINUS_ZERO
    );

    public Rook(final Camp camp) {
        super(PieceType.ROOK, camp);
    }

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }
        return availableInclinations.contains(startCoordinate.getInclination(endCoordinate));
    }

    @Override
    public boolean isCatchable(Camp otherCamp, Coordinate startCoordinate, Coordinate endCoordinate) {
        if (isSameCamp(otherCamp)) {
            return false;
        }
        return isReachableByRule(startCoordinate, endCoordinate);
    }

    @Override
    public boolean canReap() {
        return false;
    }
}
