package chess.domain.piece;

import chess.domain.Position;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

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

    public abstract List<Position> createPath(final Position source, final Position target, final MovingStrategy strategy);

    public final List<Position> findPath(final Position source, final Position target, final Color targetColor) {
        final MovingStrategy movingStrategy = strategies.findStrategy(source, target);
        if (targetColor.isSameColor(this.color)) {
            throw new IllegalStateException("아군의 기물이 존재하는 곳으로는 이동할 수 없습니다.");
        }
        return createPath(source, target, movingStrategy);
    }

    public final boolean isEmpty() {
        return color.isEmpty();
    }

    public final Color getColor() {
        return color;
    }

    public final PieceType getPieceType() {
        return pieceType;
    }
}
