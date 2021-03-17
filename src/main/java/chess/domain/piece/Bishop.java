package chess.domain.piece;

import chess.domain.position.Position;

public class Bishop extends RealPiece {
    private static final String BISHOP_WORD = "B";

    public Bishop(Position position, Color color) {
        super(position, BISHOP_WORD, color);
    }
}
