package chess.domain.piece.strategy;

import chess.domain.piece.MoveStrategy;
import chess.domain.piece.position.Path;
import chess.domain.piece.strategy.constraint.MoveConstraint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PawnMoveStrategy implements MoveStrategy {

    private final List<MoveConstraint> constraints;

    /**
     * 기본적으로 직선 1칸, 직선 2칸, 대각선 1 칸 이동 가능하다.
     * <p>
     * + 조건
     * 특정 랭크에서만 직선 2칸을 허용한다 (AND 조건)
     * <p>
     * 특정 방향으로의 이동을 허용한다. (AND 조건)
     */
    public PawnMoveStrategy(final List<MoveConstraint> constraints) {
        this.constraints = new ArrayList<>(constraints);
        this.constraints.add(new DefaultPawnMoveStrategy());
    }

    public PawnMoveStrategy(final MoveConstraint... constraints) {
        this(Arrays.asList(constraints));
    }

    @Override
    public boolean movable(final Path path) {
        return constraints.stream()
                .allMatch(it -> it.satisfy(path));
    }

    static class DefaultPawnMoveStrategy implements MoveConstraint {

        @Override
        public boolean satisfy(final Path path) {
            if (path.rankInterval() == 0) {
                return false;
            }

            return path.isUnitDistance() || isTwoVerticalMove(path);
        }

        private boolean isTwoVerticalMove(final Path path) {
            if (Math.abs(path.rankInterval()) != 2) {
                return false;
            }
            return Math.abs(path.fileInterval()) == 0;
        }
    }
}
