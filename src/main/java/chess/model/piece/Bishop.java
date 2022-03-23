package chess.model.piece;

import chess.model.Color;

public final class Bishop extends Piece{
    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean movable() {
        return false;
    }

    @Override
    public String getLetter() {
        return "b";
    }
}
