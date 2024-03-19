package domain;

import java.util.Objects;

public class Position {

    private final Horizontal horizontal;
    private final Vertical vertical;

    public Position(Horizontal horizontal, Vertical vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Position position = (Position) object;
        return Objects.equals(horizontal, position.horizontal) && Objects.equals(vertical, position.vertical);
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontal, vertical);
    }
}
