package chess.domain.piece;

import chess.domain.Position;

public final class Rook extends Piece {

    private static final String ROOK_NAME = "R";

    public Rook(Color color, Position position) {
        super(color, ROOK_NAME, position);
    }
}
