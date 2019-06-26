package model.board;

import java.util.Objects;
import java.util.stream.IntStream;

public class Coord implements Comparable<Coord> {
    static final int MIN = 0;
    static final int MAX = Board.BOARD_WIDTH;

    private static final Coord[] CACHE = IntStream.range(MIN, MAX)
                                                    .mapToObj(Coord::new)
                                                    .toArray(Coord[]::new);

    private final int val;

    static Coord of(final int val) {
        return CACHE[val];
    }

    private Coord(final int val) {
        this.val = val;
    }

    public int val() {
        return val;
    }

    public String convertToStringX() {
        return String.valueOf((char) (this.val + 'a'));
    }

    public String convertToStringY() {
        return String.valueOf(val + 1);
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Coord)) {
            return false;
        }
        final Coord rhs = (Coord) o;
        return val == rhs.val;
    }

    @Override
    public int compareTo(final Coord rhs) {
        return this.val - rhs.val;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }
}