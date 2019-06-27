package chess.domain.geometric;

import chess.domain.chess.exception.IllegalPositionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


public class Position {
    public static final int MIN_POSITION = 0;
    public static final int MAX_POSITION = 8;
    private static final List<Position> positions;


    static {
        positions = new ArrayList<>();

        for (int i = MIN_POSITION; i < MAX_POSITION; i++) {
            createPosition(i);
        }
    }

    private final int x;
    private final int y;

    private Position(final int x, final int y) {
        validatePosition(x);
        validatePosition(y);
        this.x = x;
        this.y = y;
    }

    private static void createPosition(int row) {
        for (int column = MIN_POSITION; column < MAX_POSITION; column++) {
            positions.add(new Position(row, column));
        }
    }

    private void validatePosition(final int x) {
        if (x < MIN_POSITION || x >= MAX_POSITION) {
            throw new IllegalPositionException("범위를 벗어난 포지션입니다.");
        }
    }

    public static Position create(final int x, final int y) {
        return positions.get(MAX_POSITION * x + y);
    }

    public int calculateXDistance(Position position) {
        return this.x - position.x;
    }

    public int calculateYDistance(Position position) {
        return this.y - position.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return x + "" + y;
    }
}
