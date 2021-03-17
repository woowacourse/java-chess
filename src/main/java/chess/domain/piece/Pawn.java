package chess.domain.piece;

import chess.domain.position.Position;

public class Pawn extends Piece {
    private static final String INITIAL_NAME = "P";

    public Pawn(final boolean isBlack) {
        super(isBlack, INITIAL_NAME);
    }

    public boolean canMove(Position source, Position target) {
        return false;
    }
}
