package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;

import java.util.Locale;
import java.util.Objects;

public abstract class Piece {

    protected final Color color;
    private final String name;
    private final double score;

    protected Piece(final Color color, final String name, final double score) {
        this.color = color;
        this.name = name;
        this.score = score;
    }

    public abstract void checkMovingRange(final Board board, final Position from, final Position to);

    public abstract boolean isPawn();

    public abstract boolean isKing();

    public boolean isSameColor(final Color other) {
        return color == other;
    }

    public final String getName() {
        if (color.equals(Color.BLACK)) {
            return name.toUpperCase(Locale.ROOT);
        }
        return name;
    }

    public double getScore() {
        return score;
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
