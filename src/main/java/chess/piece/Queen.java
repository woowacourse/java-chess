package chess.piece;

import chess.position.Offset;

public class Queen implements Piece {

    @Override
    public boolean canMove(final Offset offset) {
        return offset.isDiagonal() || offset.isVerticalOrHorizontal();
    }

    @Override
    public String toString() {
        return "퀸";
    }
}
