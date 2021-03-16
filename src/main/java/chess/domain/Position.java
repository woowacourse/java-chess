package chess.domain;

import javafx.geometry.Pos;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static chess.domain.Board.BOARD_SIZE;

public class Position {

    private static final Map<String, Position> positions = new HashMap<>();

    private final int x;
    private final int y;

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(int x, int y) {
        if (y < 0 || BOARD_SIZE < y || x < 0 || BOARD_SIZE < x) {
            throw new IllegalArgumentException("체스판을 넘어서는 범위입니다.");
        }

        String key = "" + x + y;
        if (!positions.containsKey(key)) {
            positions.put(key, new Position(x,y));
        }
        return positions.get(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return y == position.y &&
                x == position.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }
}
