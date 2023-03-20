package domain.piece;

import domain.position.Direction;
import domain.position.Position;

public final class Queen extends Piece {

    private static final String NAME = "Q";

    public Queen(final Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        return Direction.isStraight(source, destination) || Direction.isDiagonal(source, destination);
    }
}
