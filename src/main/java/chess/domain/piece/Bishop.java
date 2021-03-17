package chess.domain.piece;

import chess.domain.position.Position;

public class Bishop extends Piece {
    private static final String BISHOP_WORD = "B";

    public Bishop(Color color, Position position) {
        super(color, position, BISHOP_WORD);
    }
}
