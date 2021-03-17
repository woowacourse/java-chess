package chess.domain;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.IntStream;

import static chess.domain.Board.BOARD_SIZE;
import static java.util.stream.Collectors.toMap;

public class Position {

    private static final Map<String, Position> positions;

    static {
        positions = IntStream.rangeClosed(0, BOARD_SIZE - 1)
                .boxed()
                .flatMap(x -> IntStream.rangeClosed(0, BOARD_SIZE - 1)
                        .mapToObj(y -> new Position(x, y)))
                .collect(toMap(position -> "" + position.x + position.y, Function.identity()));
    }

    private final int x;
    private final int y;

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(int x, int y) {
        if (y < 0 || BOARD_SIZE - 1 < y || x < 0 || BOARD_SIZE - 1 < x) {
            throw new IllegalArgumentException("체스판을 넘어서는 범위입니다.");
        }

        String key = "" + x + y;
        return positions.get(key);
    }

    public boolean isDiagonal(Position targetPosition) {
        if (this.equals(targetPosition)) {
            return false;
        }
        int xDif = Math.abs(x - targetPosition.x);
        int yDif = Math.abs(y - targetPosition.y);
        return xDif == yDif;
    }

    public boolean isStraight(Position targetPosition) {
        if (this.equals(targetPosition)) {
            return false;
        }
        return targetPosition.x == x || targetPosition.y == y;
    }

    public boolean isKnightMove(Position targetPosition) {
        int xDif = Math.abs(x - targetPosition.x);
        int yDif = Math.abs(y - targetPosition.y);
        if (xDif == 1) {
            return yDif == 2;
        }
        if (xDif == 2) {
            return yDif == 1;
        }
        return false;
    }

    public boolean isAroundPosition(Position targetPosition) {
        if (this.equals(targetPosition)) {
            return false;
        }
        int xDif = Math.abs(x - targetPosition.x);
        int yDif = Math.abs(y - targetPosition.y);
        return xDif <= 1 && yDif <= 1;
    }

    public boolean upperThan(Position targetPosition) {
        return y > targetPosition.y;
    }

    public boolean lowerThan(Position targetPosition) {
        return y < targetPosition.y;
    }

    public int xDifference(Position targetPosition) {
        return Math.abs(x - targetPosition.x);
    }

    public int yDifference(Position targetPosition) {
        return Math.abs(y - targetPosition.y);
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

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
