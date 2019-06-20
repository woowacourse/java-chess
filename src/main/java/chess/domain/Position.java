package chess.domain;

import chess.exception.NotFoundPositionException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Position {
    private static final Map<String, Position> positions;

    private final int coordinateX;
    private final int coordinateY;

    static {
        positions = new HashMap<>();
        IntStream.rangeClosed(0, 7).forEach(Position::addPosition);
    }

    private static void addPosition(int x) {
        IntStream.rangeClosed(0, 7).forEach(y -> {
            String key = "" + x + y;
            positions.put(key, new Position(x, y));
        });
    }

    private Position(final int coordinateX, final int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public static Position of(String coordinate) {
        return positions.values().stream()
                .filter(v -> v == positions.get(coordinate))
                .findFirst()
                .orElseThrow(NotFoundPositionException::new);
    }
}
