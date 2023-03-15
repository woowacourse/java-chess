package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private final List<Position> positions;

    public Path(final List<Position> positions) {
        this.positions = positions;
    }

    public static Path ofSinglePath(final Position current, final Direction direction) {
        List<Position> positions = new ArrayList<>();
        Position next = current.findNextPosition(direction);
        if (next != null) {
            positions.add(next);
        }
        return new Path(positions);
    }

    public static Path ofMultiPath(Position current, final Direction direction) {
        List<Position> positions = new ArrayList<>();
        while ((current = current.findNextPosition(direction)) != null) {
            positions.add(current);
        }
        return new Path(positions);
    }

    public List<Position> positions() {
        return new ArrayList<>(positions);
    }
}
