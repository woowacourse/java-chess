package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Distance;

public final class InvalidPiece extends Piece {

    private static final Piece INVALID_PIECE = new InvalidPiece(Color.EMPTY);

    private InvalidPiece(Color color) {
        super(color, PieceType.INVALID);
    }

    public static Piece getInstance() {
        return INVALID_PIECE;
    }

    @Override
    public boolean movable(Distance distance, Piece target) {
        return false;
    }
}
