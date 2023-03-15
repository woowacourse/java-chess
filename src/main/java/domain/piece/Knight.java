package domain.piece;

import domain.position.Position;

public final class Knight extends Piece {

    private static final String NAME = "N";

    public Knight(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        return false;
    }
}
