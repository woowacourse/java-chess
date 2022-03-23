package chess.model.piece;

import chess.model.Color;

public final class Queen extends Piece {
    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean movable() {
        return false;
    }

    @Override
    public String getLetter() {
        return "q";
    }
}
