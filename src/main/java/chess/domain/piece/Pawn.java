package chess.domain.piece;

import chess.domain.Position;

public final class Pawn extends Piece {

    private static final String PAWN_NAME = "P";

    public Pawn(Color color, Position position) {
        super(color, PAWN_NAME, position);
    }
}
