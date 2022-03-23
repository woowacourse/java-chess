package chess.model.piece;

import chess.model.Color;
import chess.model.File;
import chess.model.Square;

public final class Rook extends Piece{


    public Rook(Color color, Square square) {
        super(color, square);
    }

    @Override
    public boolean movable(Piece targetPiece) {
        return false;
    }

    @Override
    public String getLetter() {
        return "r";
    }
}
