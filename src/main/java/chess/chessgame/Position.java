package chess.chessgame;

import java.util.ArrayList;
import java.util.List;
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

    public boolean isSameX(int x) {
        return this.x == x;
    }

    public boolean isSameX(Position input) {
        return x == input.x;
    }

    public boolean isSameY(int y) {
        return this.y == y;
    }

    public boolean isSameY(Position input) {
        return y == input.y;
    }

    public boolean isCross(Position input) {
        return Math.abs(x - input.x) == Math.abs(y - input.y);
    }

    public int computeInclination(Position input) {
        return y - input.y / x - input.x;
    }

    public List<Position> computeBetweenSameX(Position input) {
        List<Position> list = new ArrayList<>();
        int startY = Math.min(y, input.y);
        int endY = Math.max(y, input.y);

        for (int i = startY + 1; i < endY; i++) {
            list.add(new Position(x, i));
        }

        return list;
    }

    public List<Position> computeBetweenSameY(Position input) {
        List<Position> list = new ArrayList<>();
        int startX = Math.min(x, input.x);
        int endX = Math.max(x, input.x);

        for (int i = startX + 1; i < endX; i++) {
            list.add(new Position(i, y));
        }

        return list;
    }

    public List<Position> computeBetweenRightUp(Position input) {
        List<Position> list = new ArrayList<>();
        int startX = Math.max(x, input.x);
        int startY = Math.min(y, input.y);

        for (int i = 1; i < Math.abs(x - input.x); i++) {
            list.add(new Position(startX - i, startY + i));
        }
        return list;
    }

    public List<Position> computeBetweenRightDown(Position input) {
        List<Position> list = new ArrayList<>();
        int startX = Math.min(x, input.x);
        int startY = Math.min(y, input.y);

        for (int i = 1; i < Math.abs(x - input.x); i++) {
            list.add(new Position(startX + i, startY + i));
        }
        return list;
    }

    public boolean isTarget(Position coordinate, Position target) {
        return x + coordinate.x == target.x
                && y + coordinate.y == target.y;
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
