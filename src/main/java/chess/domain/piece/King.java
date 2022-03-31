package chess.domain.piece;

import chess.domain.Color;

public final class King extends Piece {
    public King(Color color) {
        super(color);
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
