package chess.domain;

import chess.domain.piece.Direction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Path {

    private final List<Position> positions;

    private Path(final Position source, final Position target) {
        positions = new ArrayList<>();

        Position currentPosition = source;
        Direction direction = source.calculateDirection(target);

        while (currentPosition != target) {
            currentPosition = currentPosition.moveTowardDirection(direction);
            positions.add(currentPosition);
        }
    }

    public List<Position> getPositions() {
        return Collections.unmodifiableList(positions);
    }
}
