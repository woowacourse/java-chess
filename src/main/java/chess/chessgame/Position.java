package chess.chessgame;


import java.util.Objects;

public class Position {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Position)) {
            return false;
        }

        Position compare = (Position) o;
        return x == compare.x && y == compare.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
