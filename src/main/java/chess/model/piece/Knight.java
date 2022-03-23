package chess.model.piece;

import chess.model.Color;
import chess.model.Square;

public final class Knight extends Piece{
    public Knight(Color color, Square square) {
        super(color, square);
    }

    @Override
    public boolean movable(Piece targetPiece) {
        return false;
    }

    @Override
    public String getLetter() {
        return "n";
    }
}
