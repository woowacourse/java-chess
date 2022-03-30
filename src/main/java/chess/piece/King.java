package chess.piece;

import chess.square.Square;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return "k";
    }

    @Override
    public boolean movable(Square source, Square target) {
        return false;
    }
}
