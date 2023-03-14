package domain.piece;

import domain.board.Position;

public final class Queen extends Piece {

    private static final String NAME = "Q";

    private Queen(final Position position) {
        super(NAME, position);
    }

    public static Piece create(final Position position) {
        return new Queen(position);
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
