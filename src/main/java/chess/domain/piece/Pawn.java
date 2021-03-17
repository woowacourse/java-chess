package chess.domain.piece;

import chess.domain.position.Position;

public class Pawn extends Piece {
    private static final String PAWN_WORD = "P";

    public Pawn(Color color, Position position) {
        super(color, position, PAWN_WORD);
    }
}
