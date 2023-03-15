package chess.domain.piece;

import chess.domain.move.Move;
import java.util.Set;

public abstract class InfinitePiece extends QuadrantPiece {

    public InfinitePiece(boolean isWhite, Set<Move> moves) {
        super(isWhite, moves);
    }

    @Override
    protected boolean compareMove(Move pieceMove, Move move) {
        return pieceMove.equals(move.getUnitMove());
    }
}
