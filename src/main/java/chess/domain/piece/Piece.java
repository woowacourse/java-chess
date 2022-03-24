package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Point;

public abstract class Piece {

    protected final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public abstract PieceType getType();

    public abstract void move(Board board, Point from, Point to);

    public abstract boolean isEmpty();
}
