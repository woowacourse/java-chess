package chess.domain.piece;

import chess.domain.position.Position;

public class Rook extends RealPiece {
    private static final String ROOK_WORD = "R";

    public Rook(Position position, Color color) {
        super(position, ROOK_WORD, color);
    }
}
