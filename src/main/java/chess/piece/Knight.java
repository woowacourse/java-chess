package chess.piece;

import chess.square.Square;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return "n";
    }

    @Override
    public boolean movable(Square source, Square target) {
        return false;
    }
}
