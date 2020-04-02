package chess.domain.position;

import chess.domain.piece.Piece;
import chess.domain.util.Direction;
import chess.exception.InvalidPositionException;
import chess.exception.OutOfBoardRangeException;

import java.util.Objects;

public class Position {
    private static final int ASCII_GAP = 96;
    public static final int END_INDEX = 8;
    public static final int START_INDEX = 1;
    public static final int ROW_SIZE = 8;

    private final int x;
    private final int y;

    public Position(String x, String y) {
        this(Integer.parseInt(x), Integer.parseInt(y));
    }

    public Position(String position) {
        this(String.valueOf(position.charAt(0) - ASCII_GAP), String.valueOf(position.charAt(1)));
        if (position.length() != 2) {
            throw new InvalidPositionException(position);
        }
    }

    public Position(int x, int y) {
        if (!isInBoardRange(x, y)) {
            throw new OutOfBoardRangeException();
        }
        this.x = x;
        this.y = y;
    }

    public Position moveBy(Direction direction) {
        return new Position(x + direction.getColumn(), y + direction.getRow());
    }

    public static boolean isInBoardRange(Piece piece) {
        return isInBoardRange(piece.getPosition());
    }

    public static boolean isInBoardRange(Position position) {
        return isInBoardRange(position.x, position.y);
    }

    public static boolean isInBoardRange(int x, int y) {
        return x <= END_INDEX && x >= START_INDEX &&
                y <= END_INDEX && y >= START_INDEX;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
