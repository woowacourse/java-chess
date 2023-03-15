package domain.piece;

import domain.position.Position;

public final class Pawn extends Piece {

    private static final String NAME = "P";

    public Pawn(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        return false;
    }
}
