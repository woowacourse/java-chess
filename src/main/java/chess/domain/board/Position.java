package chess.domain.board;

import java.util.Objects;

public class Position {
    private final int position;

    public Position(int position) {
        this.position = position;
    }

    public int subtract(Position anotherPosition) {
        return position - anotherPosition.position;
    }

    public int add(Position anotherPosition) {
        return this.position + anotherPosition.position;
    }

    public boolean isSameAs(int position) {
        return this.position == position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Position position1 = (Position)o;
        return position == position1.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
