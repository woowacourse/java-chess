package domain.piece;

import domain.position.Position;

public final class Rook extends Piece {

    private static final String NAME = "R";

    public Rook(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        return source.isStraight(destination);
    }
}
