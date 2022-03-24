package chess.domain.piece;

import chess.domain.Color;
import java.util.List;

public final class InvalidPiece extends Piece {

    private static final Piece INVALID_PIECE = new InvalidPiece(Color.WHITE);

    private InvalidPiece(Color color) {
        super(color);
    }

    @Override
    public boolean movable(List<Integer> distances, Piece target) {
        return false;
    }

    public static Piece getInstance() {
        return INVALID_PIECE;
    }
    
    @Override
    public boolean isInValid() {
        return true;
    }
}
