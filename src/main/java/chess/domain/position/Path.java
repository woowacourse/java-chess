package chess.domain.position;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Path path = (Path) o;
        return Objects.equals(movablePositions, path.movablePositions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movablePositions);
    }
}
