package chess.domain.piece;

import chess.domain.move.Move;
import java.util.Set;

public abstract class Piece {

    protected final boolean isWhite;
    protected final Set<Move> moves;

    public Piece(boolean isWhite, Set<Move> moves) {
        this.isWhite = isWhite;
        this.moves = moves;
    }

    public boolean hasSameColor(Piece otherPiece) {
        return isWhite == otherPiece.isWhite;
    }

    public boolean hasColor(boolean isWhite) {
        return this.isWhite == isWhite;
    }

    public boolean hasMove(Move move) {
        boolean hasMove = false;
        for (Move pieceMove : moves) {
            hasMove = hasMove || compareMove(pieceMove, move);
        }
        return hasMove;
    }

    protected boolean compareMove(Move pieceMove, Move move) {
        return pieceMove.equals(move);
    }

    public boolean hasAttackMove(Move attackMove) {
        return hasMove(attackMove);
    }

    public Piece touch() {
        return this;
    }
}
