package chess.domain.piece;

import chess.domain.position.Position;

public class Queen extends Piece {
    private static final String INITIAL_NAME = "Q";

    public Queen(final boolean isBlack) {
        super(isBlack, INITIAL_NAME);
    }

    @Override
    public boolean canMove(Position source, Position target, Piece piece) {
        return false;
    }
}
