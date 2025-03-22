package chess.piece;

import chess.position.Offset;

public class Rook implements Piece{

    @Override
    public boolean canMove(final Offset offset) {
        return offset.isVerticalOrHorizontal();
    }

    @Override
    public String toString() {
        return "룩";
    }
}
