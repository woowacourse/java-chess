package chess.domain.position;

import java.util.ArrayList;
import java.util.List;

public class PositionManager {
    private static List<Position> positions = new ArrayList<>();

    static {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                Position position = new Position(i, j);
                positions.add(position);
            }
        }
    }

    public static Position getMatchPosition(int x, int y) {
        return positions.stream()
                .filter(position -> position.isMatchPosition(x, y))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
