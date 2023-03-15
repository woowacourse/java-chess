package chess.domain.piece;

import chess.domain.Move;
import java.util.Set;

public abstract class FinitePiece extends QuadrantPiece {

    public FinitePiece(boolean isWhite, Set<Move> moves) {
        super(isWhite, moves);
    }

    @Override
    protected boolean compareMove(Move pieceMove, Move move) {
        return pieceMove.equals(move);
    }
}
