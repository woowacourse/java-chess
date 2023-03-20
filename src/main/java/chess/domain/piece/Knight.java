package chess.domain.piece;

import chess.domain.Position;
import chess.movingStrategy.MoveDownTwoLeft;
import chess.movingStrategy.MoveDownTwoRight;
import chess.movingStrategy.MoveLeftTwoDown;
import chess.movingStrategy.MoveLeftTwoUp;
import chess.movingStrategy.MoveRightTwoDown;
import chess.movingStrategy.MoveRightTwoUp;
import chess.movingStrategy.MoveUpTwoLeft;
import chess.movingStrategy.MoveUpTwoRight;
import chess.movingStrategy.MovingStrategies;
import chess.movingStrategy.MovingStrategy;

import java.util.Collections;
import java.util.List;

public final class Knight extends MovablePiece {

    private Knight(final Color color, final MovingStrategies strategies) {
        super(color, strategies, PieceType.KNIGHT);
    }

    public static Knight create(final Color color) {
        final List<MovingStrategy> rawStrategies = List.of(
                MoveUpTwoRight.create(), MoveUpTwoLeft.create(),
                MoveRightTwoUp.create(), MoveRightTwoDown.create(),
                MoveDownTwoRight.create(), MoveDownTwoLeft.create(),
                MoveLeftTwoDown.create(), MoveLeftTwoUp.create());
        MovingStrategies strategies = new MovingStrategies(rawStrategies);

        return new Knight(color, strategies);
    }

    @Override
    public List<Position> findPath(final Position source, final Position target,
                                   final Color targetColor) {
        strategies.findStrategy(source, target);

        if (targetColor.isSameColor(this.color)) {
            throw new IllegalStateException("아군의 기물이 존재하는 곳으로는 이동할 수 없습니다.");
        }

        return Collections.emptyList();
    }
}
