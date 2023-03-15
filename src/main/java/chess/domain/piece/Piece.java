package chess.domain.piece;

import chess.domain.Move;
import java.util.Set;

public abstract class Piece {

    private final boolean isWhite;
    protected final Set<Move> moves;

    public Piece(boolean isWhite, Set<Move> moves) {
        this.isWhite = isWhite;
        this.moves = moves;
    }

    public boolean hasSameColor(Piece otherPiece) {
        return isWhite == otherPiece.isWhite;
    }

    public boolean hasMove(Move move) {
        boolean hasMove = false;
        for (Move pieceMove : moves) {
            hasMove = hasMove || compareMove(pieceMove, move);
        }
        return hasMove;
    }

    protected abstract boolean compareMove(Move pieceMove, Move move);
}
