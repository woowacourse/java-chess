package chess.piece;

import chess.position.Movement;

public class Bishop implements Piece{

    @Override
    public boolean canMove(final Movement movement) {

        return false;
    }

    @Override
    public String toString() {
        return "숍";
    }
}
