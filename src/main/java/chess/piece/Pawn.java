package chess.piece;

import chess.board.Variation;

public class Pawn implements Piece {

    @Override
    public boolean canMove(final Variation variation) {
        return false;
    }

    @Override
    public boolean isSameTeam(final Team team) {
        return false;
    }
}
