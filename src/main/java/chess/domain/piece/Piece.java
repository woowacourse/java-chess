package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;

import java.util.Locale;
import java.util.Objects;

public abstract class Piece {

    private final Color color;
    private final String name;

    protected Piece(final Color color, final String name) {
        this.color = color;
        this.name = name;
    }

    public abstract void checkMovingRange(final Board board, final Position from, final Position to);

    public abstract boolean isPawn();

    public abstract boolean isKing();

    public abstract double getScore();

    public boolean isSameColor(final Color other) {
        return color == other;
    }

    public String getName() {
        if (color.equals(Color.BLACK)) {
            return name.toUpperCase(Locale.ROOT);
        }
        return name;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Piece)) return false;

        Piece piece = (Piece) other;

        return name.equals(piece.name) && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }
}
