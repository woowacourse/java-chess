package domain.piece.pawn;

import domain.piece.move.Coordinate;
import domain.piece.Piece;

public abstract class Pawn extends Piece {

    public abstract boolean isReachableByRule(final Coordinate start, final Coordinate end);

    @Override
    public boolean isPawn() {
        return true;
    }
}
