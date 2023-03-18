package chess.domain.piece;

import chess.domain.board.MoveType;
import chess.domain.position.Move;

public abstract class Piece {

    protected final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public abstract boolean isValidMove(Move move, MoveType moveType);

    public boolean isRightTarget(Piece target) {
        return color != target.color;
    }

    public boolean isRightTurn(Color turn) {
        return this.color == turn;
    }

    public Piece touch() {
        return this;
    }

    public abstract PieceType getType();

    public Color getColor() {
        return color;
    }
}
