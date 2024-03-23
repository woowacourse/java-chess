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
    RIGHT_UP(Map.entry(1, 1)),
    RIGHT_DOWN(Map.entry(1, -1)),
    LEFT_UP(Map.entry(-1, 1)),
    LEFT_DOWN(Map.entry(-1, -1));

    private final Map.Entry<Integer, Integer> entry;

    Direction(Map.Entry<Integer, Integer> entry) {
        this.entry = entry;
    }

    public List<Coordinate> createSlidingPath(Coordinate start) {
        List<Coordinate> coordinates = new ArrayList<>();
        int fileWeight = entry.getKey();
        int rankWeight = entry.getValue();
        int nextRank = start.getRank() + rankWeight;
        char nextFile = (char) (start.getFile() + fileWeight);

        while (nextRank >= 1 && nextRank <= 8 && nextFile >= 'a' && nextFile <= 'h') {
            coordinates.add(new Coordinate(nextRank, nextFile));
            nextRank += rankWeight;
            nextFile += fileWeight;
        }

        return coordinates;
    }
}
