package chess.domain.piece;

import chess.domain.position.Position;

public class Pawn extends RealPiece {
    private static final String PAWN_WORD = "P";

    public Pawn(Position position, Color color) {
        super(position, PAWN_WORD, color);
    }
}
