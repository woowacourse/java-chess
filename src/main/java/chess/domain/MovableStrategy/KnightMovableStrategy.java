package chess.domain.MovableStrategy;

import chess.domain.position.Position;

import java.util.Objects;

public class KnightMovableStrategy implements MovableStrategy {
    @Override
    public boolean canMove(Position source, Position target) {
        validate(source, target);
        int fileInterval = Math.abs(source.calculateFileIntervalTo(target));
        int rankInterval = Math.abs(source.calculateRankIntervalTo(target));

        return isNotExistOnAxis(fileInterval, rankInterval) && isKnightRange(fileInterval, rankInterval);
    }

    private void validate(Position source, Position target) {
        Objects.requireNonNull(source, "이동할 소스가 존재하지 않습니다.");
        Objects.requireNonNull(target, "이동할 타겟이 존재하지 않습니다.");
    }

    private boolean isNotExistOnAxis(int fileInterval, int rankInterval) {
        return fileInterval != 0 && rankInterval != 0;
    }

    private boolean isKnightRange(int fileInterval, int rankInterval) {
        return fileInterval + rankInterval == 3;
    }

    @Override
    public boolean canLeap() {
        return true;
    }
}
