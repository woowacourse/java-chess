package chess.board.piece;

import chess.board.Variation;

public class Bishop implements Piece {
    @Override
    public boolean canMove(final Variation variation) {
        return false;
    }

    @Override
    public boolean isSameTeam(final Team team) {
        return false;
    }
}
