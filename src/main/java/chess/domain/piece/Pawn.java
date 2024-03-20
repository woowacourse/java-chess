package chess.domain.piece;

import chess.domain.square.Square;

public class Pawn extends Piece {

    public Pawn(final Color color) {
        super(color, Type.PAWN);
    }

    @Override
    public boolean canMove(final Square from, final Square to) {
        int rankDiff = to.getRankIndex() - from.getRankIndex();

        return rankDiff == 1;
    }
}
