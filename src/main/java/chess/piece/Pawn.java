package chess.piece;

import chess.square.Square;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return "p";
    }

    @Override
    public boolean movable(Square source, Square target) {
        return false;
    }
}
