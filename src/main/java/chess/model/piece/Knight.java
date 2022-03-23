package chess.model.piece;

import chess.model.Color;

public final class Knight extends Piece{
    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean movable() {
        return false;
    }

    @Override
    public String getLetter() {
        return "n";
    }
}
