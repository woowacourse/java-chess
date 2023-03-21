package chess.domain.movingStrategy;

import chess.domain.Position;

import java.util.List;

public class AttackStrategies {

    private final List<MovingStrategy> strategies;

    public AttackStrategies(final List<MovingStrategy> strategies) {
        this.strategies = strategies;
    }

    public MovingStrategy findStrategy(final Position source, final Position target) {
        return strategies.stream()
                .filter(strategy -> strategy.movable(source, target))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("공격이 불가능한 지역입니다."));
    }
}
