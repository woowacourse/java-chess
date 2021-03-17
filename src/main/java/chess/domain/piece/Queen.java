package chess.domain.piece;

import chess.domain.position.Position;

public class Queen extends RealPiece {
    private static final String QUEEN_WORD = "Q";

    public Queen(Position position, Color color) {
        super(position, QUEEN_WORD, color);
    }
}
