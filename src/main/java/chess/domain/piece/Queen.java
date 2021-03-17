package chess.domain.piece;

import chess.domain.position.Position;

public class Queen extends Piece {
    private static final String QUEEN_WORD = "Q";

    public Queen(Color color, Position position) {
        super(color, position, QUEEN_WORD);
    }
}
