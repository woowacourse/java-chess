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

public final class Pawn extends NonSlidingPiece {

    private static final int INITIAL_WHITE_RANK = 2;
    private static final int INITIAL_BLACK_RANK = 7;

    private Pawn(final Color color, final MovingStrategies movingStrategies) {
        super(color, PieceType.PAWN, movingStrategies);
    }

    private static Pawn createWhitePawn() {
        final List<MovingStrategy> movingStrategies = List.of(
                new MoveUp(), new MoveLeftUp(), new MoveRightUp());
        return new Pawn(Color.WHITE, new MovingStrategies(movingStrategies));
    }

    private static Pawn createBlackPawn() {
        final List<MovingStrategy> movingStrategies = List.of(
                new MoveDown(), new MoveLeftDown(), new MoveRightDown());
        return new Pawn(Color.BLACK, new MovingStrategies(movingStrategies));
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
        final MovingStrategy movingStrategy = movingStrategies.findStrategy(source, target);
        if (movingStrategy.isAttackStrategy()) {
            final Position movePosition = movingStrategy.move(source);
            if (movePosition.equals(target) && color.isOpponent(targetColor)) {
                return Collections.emptyList();
            }
            throw new IllegalArgumentException("폰이 해당 지점으로 이동할 수 없습니다.");
        }
        if (targetColor.isEmpty()) {
            Position firstMove = movingStrategy.move(source);
            if (firstMove.equals(target)) {
                return Collections.emptyList();
            }
            Position secondMove = movingStrategy.move(firstMove);
            if (secondMove.equals(target) && isInitialPosition(source)) {
                return List.of(firstMove);
            }
        }
        throw new IllegalArgumentException("폰이 해당 지점으로 이동할 수 없습니다.");
    }

    private boolean isInitialPosition(final Position source) {
        return source.getRankOrder() == INITIAL_WHITE_RANK || source.getRankOrder() == INITIAL_BLACK_RANK;
    }
}
