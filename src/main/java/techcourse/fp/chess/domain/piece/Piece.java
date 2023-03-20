package techcourse.fp.chess.domain.piece;

import java.util.List;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.movingStrategy.MovingStrategies;

public abstract class Piece {

    protected final Color color;
    protected final MovingStrategies strategies;

    public Piece(final Color color, final MovingStrategies strategies) {
        this.color = color;
        this.strategies = strategies;
    }

    public abstract List<Position> findPath(final Position source, final Position target, final Color targetColor);

    public boolean isAlly(Piece otherPiece) {
        return color.isSameColor(otherPiece.color);
    }

    public boolean isEmpty() {
        return color.isEmpty();
    }

    public Color getColor() {
        return color;
    }
}
