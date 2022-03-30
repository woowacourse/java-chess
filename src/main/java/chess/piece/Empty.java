package chess.piece;

import chess.square.Square;

public class Empty extends Piece {

    public Empty() {
        super(Color.EMPTY);
    }

    @Override
    public String name() {
        return ".";
    }

    @Override
    public boolean movable(Square source, Square target) {
        return false;
    }
}
