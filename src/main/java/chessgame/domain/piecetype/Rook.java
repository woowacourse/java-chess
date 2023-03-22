package chessgame.domain.piecetype;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.coordinate.Inclination;

import java.util.List;

import static chessgame.domain.coordinate.Inclination.*;

public class Rook extends PieceType {

    private static final List<Inclination> availableInclinations = List.of(
            POSITIVE_INFINITY, NEGATIVE_INFINITY, ZERO, MINUS_ZERO
    );

    public Rook() {
        super(PieceTypeSymbol.ROOK);
    }

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }
        return availableInclinations.contains(startCoordinate.getInclination(endCoordinate));
    }

    @Override
    public boolean canReap() {
        return false;
    }
}
