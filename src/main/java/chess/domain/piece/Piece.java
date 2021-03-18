package chess.domain.piece;

import chess.domain.Point;

import java.util.Objects;

public abstract class Piece {
    public static final String IMPOSSIBLE_ROUTE_ERROR_MESSAGE = "기물이 이동할 수 없는 경로입니다.";
    protected final String name;
    protected final String color;
    protected Point point;

    public Piece(String name, String color, Point point) {
        this.name = name;
        this.color = color;
        this.point = point;
    }

    protected abstract boolean isMovableRoute(Piece target);

    protected abstract Point moveOneStep(Point target);

    public boolean isSameColor(String currentColor) {
        return currentColor.equals(this.color);
    }

    public boolean isNotSameColor(String currentColor) {
        return !currentColor.equals(this.color);
    }

    public void movePoint(Point target) {
        this.point = target;
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
