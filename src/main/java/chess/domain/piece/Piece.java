package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;

import java.util.Objects;

public abstract class Piece {

    private final Color color;

    protected Piece(final Color color) {
        this.color = color;
    }

    public abstract void checkMovingRange(final Board board, final Position from, final Position to);

    public abstract boolean isPawn();

    public abstract boolean isKnight();

    public abstract boolean isKing();

    public abstract double getScore();

    public boolean isSameColor(final Color other) {
        return color == other;
    }

    public boolean isSameColorWithPawn(final Color color) {
        return isSameColor(color) && isPawn();
    }

    public boolean isSameColorWithoutPawn(final Color color) {
        return isSameColor(color) && !isPawn();
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Piece)) return false;

        Piece piece = (Piece) other;

        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
