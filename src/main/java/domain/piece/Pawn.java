package domain.piece;

import domain.board.Position;

public final class Pawn extends Piece {

    private static final String NAME = "P";

    private Pawn(final Position position) {
        super(NAME, position);
    }

    public static Piece create(final Position position) {
        return new Pawn(position);
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
