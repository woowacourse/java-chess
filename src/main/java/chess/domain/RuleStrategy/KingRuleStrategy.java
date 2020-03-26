package chess.domain.RuleStrategy;

import chess.domain.position.Position;

import java.util.Objects;

public class KingRuleStrategy implements RuleStrategy {
    @Override
    public boolean canMove(Position source, Position target) {
        validate(source, target);
        int fileInterval = Math.abs(source.calculateFileIntervalTo(target));
        int rankInterval = Math.abs(source.calculateRankIntervalTo(target));

        return fileInterval <= 1 && rankInterval <= 1;
    }

    private void validate(Position source, Position target) {
        Objects.requireNonNull(source, "이동할 소스가 존재하지 않습니다.");
        Objects.requireNonNull(target, "이동할 타겟이 존재하지 않습니다.");
    }

    @Override
    public boolean canLeap() {
        return true;
    }
}
