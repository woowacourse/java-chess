package techcourse.fp.chess.domain.movingStrategy;

import java.util.List;
import techcourse.fp.chess.domain.Directions;
import techcourse.fp.chess.domain.Position;

public abstract class MovingStrategy {
    protected final Directions directions;

    public MovingStrategy(final Directions directions) {
        this.directions = directions;
    }

    public abstract List<Position> createPath(Position source, Position target);
}
