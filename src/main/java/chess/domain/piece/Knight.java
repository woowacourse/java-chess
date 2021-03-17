package chess.domain.piece;

import chess.domain.position.Position;

public class Knight extends Piece {
    private static final String KNIGHT_WORD = "N";

    public Knight(Color color, Position position) {
        super(color, position, KNIGHT_WORD);
    }
}
