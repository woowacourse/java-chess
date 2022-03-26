package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Point;

public abstract class Piece {

    protected final Color color;
    private final PieceType type;

    public Piece(Color color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSameType(PieceType type) {
        return this.type == type;
    }

    public double getPoint() {
        return this.type.getPoint();
    }

    public abstract void move(Board board, Point from, Point to);
}
