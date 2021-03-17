package chess.domain.piece;

import chess.domain.position.Position;

public class King extends Piece {
    private static final String INITIAL_NAME = "K";

    public King(final boolean isBlack) {
        super(isBlack, INITIAL_NAME);
    }

    @Override
    boolean canMove(Position source, Position target, Piece piece) {
        return false;
    }
}
