package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Path {

    private List<Position> positions;

    public Path(final Position... positions) {
        this.positions = List.of(positions);
    }

    public Path(final List<Position> positions) {
        this.positions = positions;
    }

    public boolean hasIntersection(final Set<Position> existedPositions) {
        final List<Position> intersectionPositions = new ArrayList<>(positions);
        intersectionPositions.retainAll(existedPositions);

        return !intersectionPositions.isEmpty();
    }
}
