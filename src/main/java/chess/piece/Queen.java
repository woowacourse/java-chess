package chess.piece;

import chess.square.Square;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return "q";
    }

    @Override
    public boolean movable(Square source, Square target) {
        return false;
    }
}
