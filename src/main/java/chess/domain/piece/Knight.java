package chess.domain.piece;

import chess.domain.Position;

public final class Knight extends Piece {

    private static final String KNIGHT_NAME = "K";

    public Knight(Color color, Position position) {
        super(color, KNIGHT_NAME, position);
    }
}
