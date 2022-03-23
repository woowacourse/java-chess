package chess.model.piece;

import chess.model.Color;

public final class King extends Piece {


    public King(Color color) {
        super(color);
    }

    @Override
    public boolean movable() {
        return false;
    }

    @Override
    public String getLetter() {
        return "k";
    }
}
