package chess.domain.piece;

import chess.domain.Position;
import chess.movingStrategy.MovingStrategies;
import chess.movingStrategy.MovingStrategy;

import java.util.List;

public abstract class MovablePiece extends Piece {

    public MovablePiece(final Color color, final MovingStrategies strategies, final PieceType pieceType) {
        super(color, strategies, pieceType);
        if (color.isEmpty()) {
            throw new IllegalArgumentException("기물은 흑 또는 백이어야 합니다.");
        }
    }

    @Override
    public List<Position> findPath(final Position source, final Position target,
                                   final Color targetColor) {
        final MovingStrategy movingStrategy = strategies.findStrategy(source, target);
        if (targetColor.isSameColor(this.color)) {
            throw new IllegalStateException("아군의 기물이 존재하는 곳으로는 이동할 수 없습니다.");
        }
        return createPath(source, target, movingStrategy);
    }

    protected abstract List<Position> createPath(final Position source, final Position target,
                                                 final MovingStrategy movingStrategy);
}
