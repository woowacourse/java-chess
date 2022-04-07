package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.position.Position;

public abstract class Piece {

    protected final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public abstract void validateMove(Board board, Position source, Position target);

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSameColor(Piece piece) {
        return this.color == piece.color;
    }

    public boolean isEmpty() {
        return false;
    }

    public abstract boolean isKing();

    public abstract boolean isPawn();

    public abstract String getNotation();

    public abstract double getScore();

    public Color getColor() {
        return color;
    }
}
