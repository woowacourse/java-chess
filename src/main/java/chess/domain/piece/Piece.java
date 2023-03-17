package chess.domain.piece;

import chess.domain.move.Move;
import java.util.Set;

public abstract class Piece {

    protected final Color color;
    protected final Set<Move> moves;

    public Piece(Color color, Set<Move> moves) {
        this.color = color;
        this.moves = moves;
    }

    public boolean isRightTarget(Piece target) {
        return color != target.color;
    }

    public boolean hasColor(Color color) {
        return this.color == color;
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

    public abstract PieceType getType();

    public Color getColor() {
        return color;
    }
}
