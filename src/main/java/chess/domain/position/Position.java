package chess.domain.position;

import chess.exception.InvalidPositionException;
import chess.exception.OutOfBoardRangeException;

import java.util.Objects;

public class Position {
    private static final int ASCII_GAP = 96;
    private static final int FIRST_INDEX = 0;
    public static final int END_INDEX = 8;
    public static final int START_INDEX = 1;
    public static final int ROW_SIZE = 8;

    private final String x;
    private final String y;

    public Position(String position) {
        if (position.length() != 2) {
            throw new InvalidPositionException(position);
        }
        this.x = String.valueOf(position.charAt(0));
        this.y = String.valueOf(position.charAt(1));
    }

    public Position(int x, int y) {
        this(String.valueOf((char) (x + ASCII_GAP)), String.valueOf(y));
        if (!isInBoardRange(x, y)) {
            throw new OutOfBoardRangeException();
        }
    }

    public Position(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public boolean isInBoardRange(int x, int y) {
        return x <= END_INDEX && x >= START_INDEX &&
                y <= END_INDEX && y >= START_INDEX;
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

    @Override
    public String toString() {
        return "Position{" +
                "x='" + x + '\'' +
                ", y='" + y + '\'' +
                '}';
    }
}
