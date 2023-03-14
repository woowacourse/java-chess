package domain.piece;

import domain.board.Position;

public final class King extends Piece {

    private static final String NAME = "K";

    private King(final Position position) {
        super(NAME, position);
    }

    public static Piece create(final Position position) {
        return new King(position);
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
