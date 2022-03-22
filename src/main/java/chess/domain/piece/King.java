package chess.domain.piece;

import chess.domain.Position;

public final class King extends Piece {

    private static final String KING_NAME = "K";

    public King(Color color, Position position) {
        super(color, KING_NAME, position);
    }
}
