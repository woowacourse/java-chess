package chess.domain.position;

import java.util.Objects;

public class Position {
    private static final int ASCII_GAP = 96;
    private static final int FIRST_INDEX = 0;

    private final String x;
    private final String y;

    public Position(int x, int y) {
        this(String.valueOf((char) (x + ASCII_GAP)), String.valueOf(y));
    }

    public Position(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x.charAt(FIRST_INDEX) - ASCII_GAP;
    }

    public int getY() {
        return Integer.parseInt(y);
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
