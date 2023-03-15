package chess.domain;

import java.util.Objects;

public class Side {
    private final Color color;

    public Side(final Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Side side = (Side) o;
        return color == side.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public String toString() {
        return color.name();
    }
}
