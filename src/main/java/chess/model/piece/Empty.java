package chess.model.piece;

import chess.model.Color;
import chess.model.Square;

public class Empty extends Piece {

    public Empty(Square square) {
        super(Color.NOTHING, square);
    }

    @Override
    public boolean movable(Piece targetPiece) {
        return false;
    }

    @Override
    public String getLetter() {
        return ".";
    }
}
