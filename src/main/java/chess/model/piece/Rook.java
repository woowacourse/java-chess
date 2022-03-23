package chess.model.piece;

import chess.model.Color;

public final class Rook extends Piece{


    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean movable() {
        return false;
    }

    @Override
    public String getLetter() {
        return "r";
    }
}
