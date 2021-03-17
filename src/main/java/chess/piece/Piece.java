package chess.piece;

import chess.Point;

import java.util.Objects;

public abstract class Piece {
    protected final String name;
    protected final String color;
    protected final Point point;

    public Piece(String name, String color, Point point) {
        this.name = name;
        this.color = color;
        this.point = point;
    }

    protected abstract boolean isMovable(Point target);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(name, piece.name) &&
                Objects.equals(color, piece.color) &&
                Objects.equals(point, piece.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color, point);
    }
}
