package chess.domain.piece;

import chess.domain.Position;
import chess.domain.movingStrategy.MoveDown;
import chess.domain.movingStrategy.MoveLeftDown;
import chess.domain.movingStrategy.MoveLeftUp;
import chess.domain.movingStrategy.MoveRightDown;
import chess.domain.movingStrategy.MoveRightUp;
import chess.domain.movingStrategy.MoveUp;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class Pawn extends NonSlidingPiece {

    private static final int INITIAL_WHITE_RANK = 2;
    private static final int INITIAL_BLACK_RANK = 7;

    private final AttackStrategies attackStrategies;

    private Pawn(final Color color, final MovingStrategies movingStrategies, final AttackStrategies attackStrategies) {
        super(color, PieceType.PAWN, movingStrategies);
        this.attackStrategies = attackStrategies;
    }

    private static Pawn createWhitePawn() {
        final List<MovingStrategy> movingStrategies = List.of(new MoveUp());
        final List<MovingStrategy> attackStrategies = List.of(new MoveLeftUp(), new MoveRightUp());

        return new Pawn(Color.WHITE,
                new MovingStrategies(movingStrategies),
                new AttackStrategies(Color.WHITE, attackStrategies));
    }

    private static Pawn createBlackPawn() {
        final List<MovingStrategy> movingStrategies = List.of(new MoveDown());
        final List<MovingStrategy> attackStrategies = List.of(new MoveLeftDown(), new MoveRightDown());

        return new Pawn(Color.BLACK,
                new MovingStrategies(movingStrategies),
                new AttackStrategies(Color.BLACK, attackStrategies));
    }

    public static Pawn createByColor(final Color color) {
        if (color == Color.BLACK) {
            return createBlackPawn();
        }
        if (color == Color.WHITE) {
            return createWhitePawn();
        }
        throw new AssertionError();
    }

    @Override
    public List<Position> findPath(final Position source, final Position target, final Color targetColor) {
        final Optional<MovingStrategy> strategy = attackStrategies.findStrategy(source, target);
        if (strategy.isPresent()) {
            if (attackStrategies.canAttack(source, target, targetColor, strategy.get())) {
                return Collections.emptyList();
            }
            throw new IllegalArgumentException("폰이 해당 지점으로 이동할 수 없습니다.");
        }
        final MovingStrategy movingStrategy = movingStrategies.findStrategy(source, target);
        if (isOneStepMove(source, target, targetColor)) {
            return Collections.emptyList();
        }
        if (isTwoStepMove(source, target, targetColor)) {
            return getTwoStepPath(source, movingStrategy);
        }
        throw new IllegalArgumentException("폰이 해당 지점으로 이동할 수 없습니다.");
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

    private static class AttackStrategies {

        private final Color color;
        private final List<MovingStrategy> attackStrategies;

        private AttackStrategies(final Color color, final List<MovingStrategy> attackStrategies) {
            this.color = color;
            this.attackStrategies = attackStrategies;
        }

        private Optional<MovingStrategy> findStrategy(final Position source, final Position target) {
            return attackStrategies.stream()
                    .filter(strategy -> strategy.movable(source, target))
                    .findFirst();
        }

        private boolean canAttack(final Position source, final Position target, final Color targetColor,
                                  final MovingStrategy attackStrategy) {
            final Position movePosition = attackStrategy.move(source);
            return movePosition.equals(target) && color.isOpponent(targetColor);
        }
    }
}
