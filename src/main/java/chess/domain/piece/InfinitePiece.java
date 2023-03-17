package chess.domain.piece;

import chess.domain.move.Move;
import java.util.Set;

public abstract class InfinitePiece extends QuadrantPiece {

    public InfinitePiece(Color color, Set<Move> moves) {
        super(color, moves);
    }

    @Override
    protected boolean compareMove(Move pieceMove, Move move) {
        return pieceMove.equals(move.getUnitMove());
    }
}
