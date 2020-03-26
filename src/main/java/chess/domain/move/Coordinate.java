package chess.domain.move;

import java.util.Objects;

public class Coordinate {
    private final int coordinate;
    private static final int BOARD_UP_END = 8;
    private static final int BOARD_DOWN_END = 1;
    private static final char BOARD_LEFT_END = 'a';
    private static final char BOARD_RIGHT_END = 'h';

    public Coordinate(int coordinate) {
        if (coordinate < BOARD_DOWN_END || coordinate > BOARD_UP_END) {
            throw new IllegalArgumentException();
        }
        this.coordinate = coordinate;
    }

    public Coordinate(char coordinate) {
        if (coordinate < BOARD_LEFT_END || coordinate > BOARD_RIGHT_END) {
            throw new IllegalArgumentException();
        }
        this.coordinate = coordinate - 'a' + 1;
    }

    public int getCoordinate() {
        return coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return coordinate == that.coordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }
}
