package chess.domain.board.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Path {

    private final List<Position> positions;

    public Path(final Position... positions) {
        this.positions = List.of(positions);
    }

    public Path(final List<Position> positions) {
        this.positions = List.copyOf(positions);
    }

    public boolean hasIntersection(final Set<Position> existedPositions) {
        final List<Position> intersectionPositions = new ArrayList<>(positions);
        intersectionPositions.retainAll(existedPositions);

        return !intersectionPositions.isEmpty();
    }
}
