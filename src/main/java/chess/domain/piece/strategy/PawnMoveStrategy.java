package chess.domain.piece.strategy;

import chess.domain.piece.MoveStrategy;
import chess.domain.piece.position.Path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PawnMoveStrategy implements MoveStrategy {

    private final List<MoveStrategy> moveStrategies;

    /**
     * 기본적으로 직선 1칸, 직선 2칸, 대각선 1 칸 이동 가능하다.
     * <p>
     * + 조건
     * 특정 랭크에서만 직선 2칸을 허용한다 (AND 조건)
     * <p>
     * 특정 방향으로의 이동을 허용한다. (AND 조건)
     */
    public PawnMoveStrategy(final List<MoveStrategy> moveStrategies) {
        this.moveStrategies = new ArrayList<>(moveStrategies);
        this.moveStrategies.add(new DefaultPawnMoveStrategy());
    }

    public PawnMoveStrategy(final MoveStrategy... moveStrategies) {
        this(Arrays.asList(moveStrategies));
    }

    @Override
    public boolean movable(final Path path) {
        return moveStrategies.stream()
                .allMatch(it -> it.movable(path));
    }

    static class DefaultPawnMoveStrategy implements MoveStrategy {

        @Override
        public boolean movable(final Path path) {
            if (path.rankInterval() == 0) {
                return false;
            }

            return path.isUnitDistance() || isStraightTwoPath(path);
        }

        private boolean isStraightTwoPath(final Path path) {
            if (Math.abs(path.rankInterval()) != 2) {
                return false;
            }
            return Math.abs(path.fileInterval()) == 0;
        }

    }
}
