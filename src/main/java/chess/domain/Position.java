package chess.domain;

import chess.domain.Coordinate;

import java.util.Objects;

public class Position {
    private final Coordinate x;
    private final Coordinate y;

    public Position(int x, char y) {
        this(new Coordinate(x), new Coordinate(y));
    }

    public Position(Coordinate x, Coordinate y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(x, position.x) &&
                Objects.equals(y, position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
