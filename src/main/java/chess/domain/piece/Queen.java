package chess.domain.piece;

import chess.domain.Position;

public final class Queen extends Piece {

    private static final String QUEEN_NAME = "Q";

    public Queen(Color color, Position position) {
        super(color, QUEEN_NAME, position);
    }
}
