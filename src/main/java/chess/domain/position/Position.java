package chess.domain.position;

import java.util.Objects;

public class Position implements Comparable<Position> {
    private final Horizontal horizontal;
    private final Vertical vertical;

    public Position(final Horizontal horizontal, final Vertical vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public Position(final String horizontal, final String vertical) {
        this(Horizontal.of(horizontal), Vertical.of(vertical));
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

    public Horizontal getHorizontal() {
        return horizontal;
    }

    public Vertical getVertical() {
        return vertical;
    }

    @Override
    public int compareTo(Position position) {
        if (vertical.isSameValue(position.vertical)) {
            return horizontal.getValue() - position.horizontal.getValue();
        }
        return position.vertical.getValue() - vertical.getValue();
    }
}
