package chess.domain.position;

import java.util.Locale;
import java.util.Objects;

public class Position {
    private final Horizontal horizontal;
    private final Vertical vertical;

    public Position(final String horizontal, final String vertical) {
        this.horizontal = Horizontal.of(horizontal);
        this.vertical = Vertical.of(vertical);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return horizontal == position.horizontal && vertical == position.vertical;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontal, vertical);
    }
}
