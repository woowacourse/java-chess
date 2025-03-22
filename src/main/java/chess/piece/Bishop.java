package chess.piece;

import chess.position.Offset;

public class Bishop implements Piece {

    @Override
    public boolean canMove(final Offset offset) {
        return offset.isDiagonal();
    }

    @Override
    public String toString() {
        return "숍";
    }
}
