package chess.domain.piece;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position implements Comparable<Position> {
    public static final int START_POSITION_Y = 0;
    public static final int START_POSITION_X = 0;
    public static final int END_POSITION_Y = 8;
    public static final int END_POSITION_X = 8;
    private static final Map<String, Position> POSITIONS = new HashMap<>();

    private final int x;
    private final int y;

    static {
        for (int y = START_POSITION_Y; y < END_POSITION_Y; y++) {
            for (int x = START_POSITION_X; x < END_POSITION_X; x++) {
                POSITIONS.put(key(x, y), new Position(x, y));
            }
        }
    }

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(int x, int y) {
        return from(key(x, y));
    }

    public static Position from(String key) {
        Position position = POSITIONS.get(key.toLowerCase());
        if (position == null) {
            throw new IllegalArgumentException("체스판 범위에 벗어난 위치 값입니다.");
        }
        return position;
    }

    public static String key(int x, int y) {
        return (char)('a' + x) + String.valueOf(1 + y);
    }

    public static Collection<Position> values() {
        return Collections.unmodifiableCollection(POSITIONS.values());
    }

    public Position add(int x, int y) {
        return of(this.x + x, this.y + y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equalsX(int x) {
        return this.x == x;
    }

    public boolean equalsY(int y) {
        return this.y == y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position)o;
        return x == position.x &&
            y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Position position) {
        if (y > position.y) {
            return -1;
        }
        if (y < position.y) {
            return 1;
        }
        return x - position.x;
    }

}
