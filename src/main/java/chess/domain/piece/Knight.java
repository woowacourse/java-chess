package chess.domain.piece;

import chess.domain.Position;
import chess.domain.movingStrategy.MoveDownTwoLeft;
import chess.domain.movingStrategy.MoveDownTwoRight;
import chess.domain.movingStrategy.MoveLeftTwoDown;
import chess.domain.movingStrategy.MoveLeftTwoUp;
import chess.domain.movingStrategy.MoveRightTwoDown;
import chess.domain.movingStrategy.MoveRightTwoUp;
import chess.domain.movingStrategy.MoveUpTwoLeft;
import chess.domain.movingStrategy.MoveUpTwoRight;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

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
    protected List<Position> createPath(final Position source, final Position target, final MovingStrategy movingStrategy) {
        return Collections.emptyList();
    }
}
