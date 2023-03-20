package chess.domain.piece;

import chess.domain.Position;
import chess.movingStrategy.MovingStrategies;

import java.util.List;

public abstract class Piece {

    protected final PieceType pieceType;
    protected final Color color;
    protected final MovingStrategies strategies;

    public Piece(final Color color, final MovingStrategies strategies, final PieceType pieceType) {
        this.color = color;
        this.strategies = strategies;
        this.pieceType = pieceType;
    }

    public abstract List<Position> findPath(final Position source, final Position target, final Color targetColor);

    public boolean isEmpty() {
        return color.isEmpty();
    }

    public Color getColor() {
        return color;
    }
}
