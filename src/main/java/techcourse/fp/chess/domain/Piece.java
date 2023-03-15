package techcourse.fp.chess.domain;

import java.util.List;
import techcourse.fp.movingStrategy.MovingStrategies;

public abstract class Piece {

    protected final Color color;

    protected MovingStrategies strategies;

    public Piece(final Color color) {
        this.color = color;
    }

    public abstract List<Position> findPath(final Position sourcePosition, final Position targetPosition, final Color targetColor);
}
