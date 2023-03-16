package techcourse.fp.chess.domain;

import java.util.List;
import techcourse.fp.chess.movingStrategy.MovingStrategies;

public abstract class Piece {

    protected final Color color;

    protected MovingStrategies strategies;

    public Piece(final Color color) {
        this.color = color;
    }

    public Piece(final Color color, final MovingStrategies strategies) {
        this.color = color;
        this.strategies = strategies;
    }

    public abstract List<Position> findPath(final Position source, final Position target, final Color targetColor);

    public boolean isEmpty() {
        return color.isEmpty();
    }

    public Color getColor() {
        return color;
    }
}
