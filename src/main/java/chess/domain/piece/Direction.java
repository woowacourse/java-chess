package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import chess.domain.board.Coordinate;

enum Direction {

    LEFT(Map.entry(-1, 0)),
    RIGHT(Map.entry(1, 0)),
    UP(Map.entry(0, 1)),
    DOWN(Map.entry(0, -1)),
    UP_RIGHT(Map.entry(1, 1)),
    DOWN_RIGHT(Map.entry(1, -1)),
    UP_LEFT(Map.entry(-1, 1)),
    DOWN_LEFT(Map.entry(-1, -1)),
    UP_UP_RIGHT(Map.entry(1, 2)),
    UP_UP_LEFT(Map.entry(-1, 2)),
    LEFT_LEFT_UP(Map.entry(-2, 1)),
    LEFT_LEFT_DOWN(Map.entry(-2, -1)),
    RIGHT_RIGHT_UP(Map.entry(2, 1)),
    RIGHT_RIGHT_DOWN(Map.entry(2, -1)),
    DOWN_DOWN_LEFT(Map.entry(-1, -2)),
    DOWN_DOWN_RIGHT(Map.entry(1, -2)),

    ;

    private final Map.Entry<Integer, Integer> value;

    Direction(Map.Entry<Integer, Integer> value) {
        this.value = value;
    }

    public List<Coordinate> createSlidingPath(Coordinate start) {
        List<Coordinate> coordinates = new ArrayList<>();
        int fileWeight = value.getKey();
        int rankWeight = value.getValue();
        int nextRank = start.getRank() + rankWeight;
        char nextFile = (char) (start.getFile() + fileWeight);

        while (nextRank >= 1 && nextRank <= 8 && nextFile >= 'a' && nextFile <= 'h') {
            coordinates.add(new Coordinate(nextRank, nextFile));
            nextRank += rankWeight;
            nextFile += fileWeight;
        }

        return coordinates;
    }

    public Map.Entry<Integer, Integer> getValue() {
        return value;
    }
}
