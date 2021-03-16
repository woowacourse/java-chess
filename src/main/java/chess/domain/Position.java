package chess.domain;

import java.util.ArrayList;
import java.util.List;
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

    public static List<Position> positions() {
        return new ArrayList<>(positions.values());
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
