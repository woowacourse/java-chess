package chess.domain.piece;

import chess.domain.Color;
import java.util.List;

public final class InvalidPiece extends Piece {

    private static final Piece INVALID_PIECE = new InvalidPiece(Color.EMPTY);

    private InvalidPiece(Color color) {
        super(color);
    }

    public static Piece getInstance() {
        return INVALID_PIECE;
    }

    @Override
    public boolean movable(List<Integer> distances, Piece target) {
        return false;
    }

    @Override
    public boolean isInValid() {
        return true;
    }
}
