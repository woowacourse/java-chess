package chess.model.piece;

import chess.model.Color;

public class Empty extends Piece {

    public Empty() {
        super(Color.NOTHING);
    }

    @Override
    public boolean movable() {
        return false;
    }

    @Override
    public String getLetter() {
        return ".";
    }
}
