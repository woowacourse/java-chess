package domain.piece.pawn;

import domain.piece.move.Coordinate;
import domain.piece.Piece;

public abstract class Pawn implements Piece {

    public abstract boolean isReachableByRuleWhenFirstMove(final Coordinate startCoordinate, final Coordinate endCoordinate);
    public abstract boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate);

    @Override
    public boolean isPawn() {
        return true;
    }
}
