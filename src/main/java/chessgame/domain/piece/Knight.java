package chessgame.domain.piece;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.coordinate.Inclination;
import chessgame.domain.chessgame.Camp;

import java.util.List;

import static chessgame.domain.coordinate.Inclination.*;

public class Knight extends Piece {

    private static final List<Inclination> availableInclinations = List.of(
            TWO, MINUS_TWO, HALF, MINUS_HALF
    );
    private static final List<Coordinate> availableCoordinateDifferences = List.of(
            Coordinate.fromOnBoard(1, 2),
            Coordinate.fromOnBoard(2, 1)
    );

    public Knight(final Camp camp) {
        super(PieceType.KNIGHT, camp);
    }

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }
        return availableInclinations.contains(startCoordinate.getInclination(endCoordinate)) &&
                availableCoordinateDifferences.contains(startCoordinate.minusWithAbsoluteValue(endCoordinate));
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
        return true;
    }
}
