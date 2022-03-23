package chess.model.piece;

import chess.model.Color;
import chess.model.Square;

public final class Bishop extends Piece{
    public Bishop(Color color, Square square) {
        super(color, square);
    }

    @Override
    public boolean movable(Piece targetPiece) {
        return false;
    }

    @Override
    public String getLetter() {
        return "b";
    }
}
