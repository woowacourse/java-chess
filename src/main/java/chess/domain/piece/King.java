package chess.domain.piece;

import chess.domain.position.Position;

public class King extends RealPiece {
    private static final String KING_WORD = "K";

    public King(Position position, Color color) {
        super(position, KING_WORD, color);
    }
}
