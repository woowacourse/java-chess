package chess.domain;

import java.util.Objects;

public class Position {
    private final String x;
    private final String y;

    Position(int x, int y) {
        this(String.valueOf((char)(x + 96)), String.valueOf(y));
    }

    Position(String x, String y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return Objects.equals(x, position.x) &&
                Objects.equals(y, position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
