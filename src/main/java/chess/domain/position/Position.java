package chess.domain.position;

import chess.domain.grid.Column;
import chess.domain.grid.Row;

import java.util.Objects;

public final class Position {
    private static final char MIN_X_RANGE = Column.FIRST.getName();
    private static final char MAX_X_RANGE = Column.EIGHTH.getName();
    private static final char MIN_Y_RANGE = Row.FIRST.getName();
    private static final char MAX_Y_RANGE = Row.EIGHTH.getName();

    private final Column x;
    private final Row y;

    public Position(final String position) {
        this(position.charAt(0), position.charAt(1));
    }

    public Position(final char x, final char y) {
        this.x = Column.column(x);
        this.y = Row.row(y);
    }

    public char x() {
        return x.getName();
    }

    public char y() {
        return y.getName();
    }

    public boolean isInValidRange() {
        return x.getName() >= MIN_X_RANGE && x.getName() <= MAX_X_RANGE
                && y.getName() >= MIN_Y_RANGE && y.getName() <= MAX_Y_RANGE;
    }

    public Position next(final int xDegree, final int yDegree) {
        return new Position((char) (x.getName() + xDegree), (char) (y.getName() + yDegree));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
