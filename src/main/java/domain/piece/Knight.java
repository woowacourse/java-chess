package domain.piece;

import domain.board.Position;

public final class Knight extends Piece {

    private static final String NAME = "N";

    private Knight(final Position position) {
        super(NAME, position);
    }

    public static Piece create(final Position position) {
        return new Knight(position);
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
