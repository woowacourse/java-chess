package position;

import java.util.Objects;

public class Position {

    private static final int MIN_POSITION = 0;
    private static final int MAX_POSITION = 7;

    private int position;

    public Position(int position) {
        this.position = position;
    }

    public int getMinusPosition(Position position) {
        return position.position - this.position;
    }

    public void move(int nextPosition) {
        if (nextPosition < MIN_POSITION || MAX_POSITION < nextPosition) {
            throw new IllegalArgumentException("움직일 수 있는 위치가 아닙니다.");
        }
        position = nextPosition;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position1 = (Position) o;
        return position == position1.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
