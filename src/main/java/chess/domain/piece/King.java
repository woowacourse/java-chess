package chess.domain.piece;

import chess.domain.Position;
import chess.movingStrategy.MoveDown;
import chess.movingStrategy.MoveLeft;
import chess.movingStrategy.MoveLeftDown;
import chess.movingStrategy.MoveLeftUp;
import chess.movingStrategy.MoveRight;
import chess.movingStrategy.MoveRightDown;
import chess.movingStrategy.MoveRightUp;
import chess.movingStrategy.MoveUp;
import chess.movingStrategy.MovingStrategies;
import chess.movingStrategy.MovingStrategy;

import java.util.Collections;
import java.util.List;

public final class King extends MovablePiece {

    private King(final Color color, final MovingStrategies strategies) {
        super(color, strategies, PieceType.KING);
    }

    public static King create(final Color color) {
        final List<MovingStrategy> rawStrategies = List.of(
                new MoveRightUp(), new MoveRightDown(),
                new MoveLeftDown(), new MoveLeftUp(),
                new MoveUp(), new MoveDown(),
                new MoveLeft(), new MoveRight());
        MovingStrategies strategies = new MovingStrategies(rawStrategies);

        return new King(color, strategies);
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

    private List<Position> createPath(final Position source, final Position target,
                                      final MovingStrategy movingStrategy) {
        final Position moveResult = movingStrategy.move(source);

        if (!moveResult.equals(target)) {
            throw new IllegalArgumentException("킹은 한 칸만 이동 가능합니다.");
        }

        return Collections.emptyList();
    }
}
