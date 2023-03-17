package chess.domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Path {

    public static final String OBSTACLE_EXIST_MESSAGE = "중간에 다른 기물이 존재합니다.";

    private List<Position> positions;

    public Path(final Position... positions) {
        this.positions = List.of(positions);
    }

    public Path(final List<Position> positions) {
        this.positions = positions;
    }

    public void validateObstacle(final Set<Position> keySet) {
        final List<Position> positions = new ArrayList<>(this.positions);
        positions.retainAll(keySet);
        if (positions.size() != 0) {
            throw new IllegalArgumentException(OBSTACLE_EXIST_MESSAGE);
        }
    }
}
