package domain.piece;

import domain.position.Direction;
import domain.position.Position;

public final class Bishop extends Piece {

    private static final String NAME = "B";

    public Bishop(final Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        return Direction.isDiagonal(source, destination);
    }
}
