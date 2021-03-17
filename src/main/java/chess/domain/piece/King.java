package chess.domain.piece;

import chess.domain.position.Position;

public class King extends Piece {
    private static final String KING_WORD = "K";

    public King(Color color, Position position) {
        super(color, position, KING_WORD);
    }
}
