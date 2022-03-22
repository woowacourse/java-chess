package chess.domain.piece;

import chess.domain.Position;

public final class Bishop extends Piece {

    private static final String BISHOP_NAME  = "B";

    public Bishop(Color color, Position position) {
        super(color, BISHOP_NAME, position);
    }
}
