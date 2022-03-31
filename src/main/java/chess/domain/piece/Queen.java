package chess.domain.piece;

import chess.domain.Color;

public final class Queen extends Piece {
    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean isQueen() {
        return true;
    }
}
