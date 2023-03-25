package chess.domain.position;

import java.util.List;

public class Path {

    private final List<Position> movablePositions;

    public Path(final List<Position> movablePositions) {
        this.movablePositions = movablePositions;
    }

    public void checkMovable(final Position target) {
        if (!movablePositions.contains(target)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }
}
