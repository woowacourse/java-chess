package chess.domain.piece;

import java.util.Set;

import chess.domain.move.Move;

public abstract class InfinitePiece extends Piece {

    public InfinitePiece(boolean isWhite, Set<Move> moves) {
        super(isWhite, moves);
    }

    @Override
    protected boolean compareMove(Move pieceMove, Move move) {
        return pieceMove.equals(move.getUnitMove());
    }
}
