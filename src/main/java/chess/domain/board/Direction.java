package chess.domain.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum Direction {

    LEFT(Map.entry(-1, 0)),
    RIGHT(Map.entry(1, 0)),
    UP(Map.entry(0, 1)),
    DOWN(Map.entry(0, -1)),
    RIGHT_UP(Map.entry(1, 1)),
    RIGHT_DOWN(Map.entry(1, -1)),
    LEFT_UP(Map.entry(-1, 1)),
    LEFT_DOWN(Map.entry(-1, -1));

    private final Map.Entry<Integer, Integer> gradient;

    Direction(Map.Entry<Integer, Integer> gradient) {
        this.gradient = gradient;
    }

    public static Direction of(Coordinate start, Coordinate destination) {
        return Arrays.stream(values())
                .filter(it -> it.gradient.equals(destination.compare(start)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("같은 곳으로는 이동할 수 없습니다."));
    }

    public List<Coordinate> createPath(final Coordinate start) {
        List<Coordinate> coordinates = new ArrayList<>();
        int fileWeight = gradient.getKey();
        int rankWeight = gradient.getValue();
        Coordinate coordinate = start;
        while (coordinate.canMove(fileWeight, rankWeight)) {
            coordinate = coordinate.move(fileWeight, rankWeight);
            coordinates.add(coordinate);
        }
        return coordinates;
    }
}
