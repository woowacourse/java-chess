package chess.model.piece;

import chess.model.square.Square;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return "r";
    }

    @Override
    public boolean movable(Square source, Square target) {
        return false;
    }
}
