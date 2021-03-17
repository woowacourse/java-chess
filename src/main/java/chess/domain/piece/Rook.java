package chess.domain.piece;

import chess.domain.position.Position;

public class Rook extends Piece {
    private static final String ROOK_WORD = "R";

    public Rook(Color color, Position position) {
        super(color, position, ROOK_WORD);
    }
}
