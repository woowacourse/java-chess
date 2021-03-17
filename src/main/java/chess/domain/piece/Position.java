package chess.domain.piece;

import java.util.Objects;

public class Position {
    private char x;
    private char y;

    private Position(char x, char y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(char x, char y) {
        return new Position(x, y);
    }

    public char getX() {
        return x;
    }

    public char getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
