package chess.domains.position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Position implements Comparable<Position> {
    private static final Map<String, Position> cachedPositions;

    static {
        Map<String, Position> positions = new HashMap<>();
        for (char x = 'a'; x <= 'h'; x++) {
            for (int y = 1; y <= 8; y++) {
                Position position = new Position(x, y);
                positions.put(x + String.valueOf(y), position);
            }
        }
        cachedPositions = positions;
    }

    private char x;
    private int y;

    public Position(char x, int y) {
        this.x = x;
        this.y = y;
    }

    public static List<String> fromRow(String row) {
        return cachedPositions.keySet().stream()
                .filter(key -> key.endsWith(row)).sorted().collect(Collectors.toList());
    }

    public static Position ofPositionName(String positionName) {
        return cachedPositions.get(positionName);
    }

    @Override
    public int compareTo(Position o) {
        if (y > o.y) {
            return -1;
        }
        if (y == o.y && x < o.x) {
            return -1;
        }
        return 1;
    }
}
