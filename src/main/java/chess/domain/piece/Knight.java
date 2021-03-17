package chess.domain.piece;

import chess.domain.position.Position;

public class Knight extends Piece {
    private static final String INITIAL_NAME = "N";

    public Knight(final boolean isBlack) {
        super(isBlack, INITIAL_NAME);
    }

    @Override
    boolean canMove(final Position source, final Position target, final Piece piece) {
        return false;
    }
}
