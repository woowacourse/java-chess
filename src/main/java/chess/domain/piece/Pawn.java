package chess.domain.piece;

import chess.domain.Position;
import chess.movingStrategy.MoveDown;
import chess.movingStrategy.MoveLeftDown;
import chess.movingStrategy.MoveLeftUp;
import chess.movingStrategy.MoveRightDown;
import chess.movingStrategy.MoveRightUp;
import chess.movingStrategy.MoveUp;
import chess.movingStrategy.MovingStrategies;
import chess.movingStrategy.MovingStrategy;

import java.util.Collections;
import java.util.List;

public final class Pawn extends MovablePiece {

    private static final int INITIAL_WHITE_RANK = 2;
    private static final int INITIAL_BLACK_RANK = 7;

    private Pawn(final Color color, final MovingStrategies strategies) {
        super(color, strategies, PieceType.PAWN);
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

    @Override
    protected List<Position> createPath(final Position source, final Position target, final MovingStrategy movingStrategy) {
        return Collections.emptyList();
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
