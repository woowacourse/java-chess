package chess.domain.position;

import java.util.ArrayList;
import java.util.List;

public class Positions {
    private static final int MIN_COORDINATE = 1;
    private static final int MAX_COORDINATE = 8;

    private static List<Position> positions = new ArrayList<>();

    static {
        for (int i = MIN_COORDINATE; i <= MAX_COORDINATE; i++) {
            fillRankPosition(i);
        }
    }

    private static void fillRankPosition(int i) {
        for (int j = MIN_COORDINATE; j <= MAX_COORDINATE; j++) {
            Position position = new Position(i, j);
            positions.add(position);
        }
    }

    public static Position matchWith(int x, int y) {
        return positions.stream()
                .filter(position -> position.isMatchPosition(x, y))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
