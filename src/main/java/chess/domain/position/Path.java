package chess.domain.position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Path {

    public static final String OBSTACLE_EXIST_MESSAGE = "중간에 다른 기물이 존재합니다.";

    private final List<Position> positions;

    public Path(final Collection<Position> positions) {
        this.positions = new ArrayList<>(positions);
    }

    public Path(final Position... positions) {
        this.positions = List.of(positions);
    }

    public void validateObstacle(final Set<Position> keySet) {
        final List<Position> positions = new ArrayList<>(this.positions);
        positions.retainAll(keySet);
        if (positions.size() != 0) {
            throw new IllegalArgumentException(OBSTACLE_EXIST_MESSAGE);
        }
    }
}
