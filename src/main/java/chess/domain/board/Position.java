package chess.domain.board;

import chess.domain.piece.DirectionType;
import chess.exception.NotFoundPositionException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Position {
    private static final int MIN_BOUND = 0;
    private static final int MAX_BOUND = 7;
    private static final String STRING_MAKER = "";
    private static final Map<String, Position> positions;

    private final Coordinate coordinateX;
    private final Coordinate coordinateY;

    static {
        positions = new HashMap<>();
        IntStream.rangeClosed(MIN_BOUND, MAX_BOUND).forEach(Position::addPosition);
    }

    private static void addPosition(int x) {
        IntStream.rangeClosed(MIN_BOUND, MAX_BOUND).forEach(y -> {
            String key = makeKey(x, y);
            positions.put(key, new Position(x, y));
        });
    }

    private static String makeKey(int x, int y) {
        return STRING_MAKER + x + y;
    }

    private Position(final int coordinateX, final int coordinateY) {
        this.coordinateX = Coordinate.of(coordinateX);
        this.coordinateY = Coordinate.of(coordinateY);
    }

    public static Position of(String coordinate) {
        return positions.values().stream()
                .filter(v -> v == positions.get(coordinate))
                .findFirst()
                .orElseThrow(NotFoundPositionException::new);
    }

    public Position hopNextPosition(DirectionType direction) {
        int movedX = coordinateX.calculate(direction.getX());
        int movedY = coordinateY.calculate(direction.getY());

        return of(makeKey(movedX, movedY));
    }
}
