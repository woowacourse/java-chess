package domain.piece;

import domain.board.Board;
import domain.position.Position;

public abstract class Piece {

    private final boolean isBlack;

    protected Piece(boolean color) {
        this.isBlack = color;
    }

    public Piece() {
        this.isBlack = false;
    }

    public abstract boolean canMove(Board board, Position source, Position target);

    public boolean isSameColor(Piece piece) {
        return piece.isNotEmpty() && this.isBlack == piece.isBlack;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isNotEmpty() {
        return true;
    }

    public boolean isEmpty() {
        return false;
    }

}
