package techcourse.fp.chess.domain.piece;

import java.util.Collections;
import java.util.List;
import techcourse.fp.chess.domain.Color;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.movingStrategy.MoveDown;
import techcourse.fp.chess.movingStrategy.MoveLeftDown;
import techcourse.fp.chess.movingStrategy.MoveLeftUp;
import techcourse.fp.chess.movingStrategy.MoveRightDown;
import techcourse.fp.chess.movingStrategy.MoveRightUp;
import techcourse.fp.chess.movingStrategy.MoveUp;
import techcourse.fp.chess.movingStrategy.MovingStrategies;
import techcourse.fp.chess.movingStrategy.MovingStrategy;

public final class Pawn extends MovablePiece {

    private static final int INITIAL_WHITE_RANK = 2;
    private static final int INITIAL_BLACK_RANK = 7;

    private Pawn(final Color color, final MovingStrategies strategies) {
        super(color, strategies);
    }

    public static Pawn createByColor(final Color color) {
        if (color == Color.BLACK) {
            return new Pawn(color, initBlackPawnStrategies());
        }

        if (color == Color.WHITE) {
            return new Pawn(color, initWhitePawnStrategies());
        }

        throw new AssertionError();
    }

    private static MovingStrategies initBlackPawnStrategies() {
        final List<MovingStrategy> movingStrategies = List.of(new MoveDown(), new MoveLeftDown(),
                new MoveRightDown());
        return new MovingStrategies(movingStrategies);
    }

    private static MovingStrategies initWhitePawnStrategies() {
        final List<MovingStrategy> movingStrategies = List.of(new MoveUp(), new MoveLeftUp(),
                new MoveRightUp());
        return new MovingStrategies(movingStrategies);
    }


    @Override
    public List<Position> findPath(final Position source, final Position target, final Color targetColor) {
        final MovingStrategy movingStrategy = strategies.findStrategy(source, target);

        if (isAttack(source, target, targetColor)) {
            return Collections.emptyList();
        }

        if (isOneStepMove(source, target, targetColor)) {
            return Collections.emptyList();
        }

        if (isTwoStepMove(source, target, targetColor)) {
            return getTwoStepPath(source, movingStrategy);
        }

        throw new IllegalArgumentException("폰이 해당 지점으로 이동할 수 없습니다.");
    }

    private boolean isAttack(final Position source, final Position target, final Color targetColor) {
        return source.isOnDiagonal(target) && targetColor.isOpponent(this.color);
    }

    private boolean isOneStepMove(final Position source, final Position target, final Color targetColor) {
        return source.isUpDown(target) && targetColor.isEmpty();
    }

    private boolean isTwoStepMove(final Position source, final Position target, final Color targetColor) {
        return isMovableTwoStep(source) && source.isUpDownTwo(target) && targetColor.isEmpty();
    }

    private List<Position> getTwoStepPath(final Position source, final MovingStrategy movingStrategy) {
        return List.of(movingStrategy.move(source));
    }

    private boolean isMovableTwoStep(final Position source) {
        if (color.isWhite()) {
            return source.getRankOrder() == INITIAL_WHITE_RANK;
        }

        if (color.isBlack()) {
            return source.getRankOrder() == INITIAL_BLACK_RANK;
        }

        return false;
    }
}
