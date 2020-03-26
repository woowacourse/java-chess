package chess.domain.Piece;

import java.util.Objects;

public class Distance {
    private final int x;
    private final int y;

    public Distance(int x, int y) {
        this.x = Math.abs(x);
        this.y = Math.abs(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance = (Distance) o;
        return x == distance.x &&
                y == distance.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
