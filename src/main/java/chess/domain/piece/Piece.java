package chess.domain.piece;

import chess.domain.board.MoveType;
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

    public boolean isValidMove(Move move, MoveType moveType) {
        return checkContainment(moves, move);
    }

    protected boolean checkContainment(Set<Move> moves, Move move) {
        boolean isContaining = false;
        for (Move pieceMove : moves) {
            isContaining = isContaining || compareMove(pieceMove, move);
        }
        return isContaining;
    }

    protected boolean compareMove(Move pieceMove, Move move) {
        return pieceMove.equals(move);
    }

    public Piece touch() {
        return this;
    }

    public abstract PieceType getType();

    public Color getColor() {
        return color;
    }
}
