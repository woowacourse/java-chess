package chess.piece;

import chess.position.Offset;

public class King implements Piece {

    @Override
    public boolean canMove(final Offset offset) {
        return !offset.isDiagonal() && offset.hasOneMovement();
    }

    @Override
    public String toString() {
        return "킹";
    }
}
