package chess.domain.piece;

import java.util.List;

import chess.domain.Position;
import chess.movingStrategy.MovingStrategies;

public abstract class Piece {

    protected final Color color;
    protected final MovingStrategies strategies;

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
