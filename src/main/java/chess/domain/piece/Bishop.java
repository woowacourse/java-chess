package chess.domain.piece;

import chess.domain.position.Position;

public class Bishop extends Piece {
    private static final String INITIAL_NAME = "B";

    public Bishop(final boolean isBlack) {
        super(isBlack, INITIAL_NAME);
    }

    @Override
    public boolean canMove(Position source, Position target, Piece piece) {
        return false;
    }
}
