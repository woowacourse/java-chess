package domain.piece;

import domain.position.Position;

public final class Queen extends Piece {

    private static final String NAME = "Q";

    public Queen(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        return source.isStraight(destination) || source.isDiagonal(destination);
    }
}
