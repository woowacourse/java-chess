package chess.domain.piece;

import chess.domain.Point;

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

//    protected abstract Point moveTo(Point target);
//
    protected abstract boolean isMovableRoute(Point target);

    public boolean isSameColor(String currentColor) {
        return currentColor.equals(this.color);
    }

    public String getName() {
        return name;
    }

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
