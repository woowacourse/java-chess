package chess.domain.piece;

import chess.domain.position.Position;

public class Knight extends RealPiece {
    private static final String KNIGHT_WORD = "N";

    public Knight(Position position,Color color) {
        super(position, KNIGHT_WORD, color);
    }
}
