package chess.domain.movingStrategy;

import chess.domain.game.Position;

import java.util.List;

public final class MovingStrategies {

    private final List<MovingStrategy> strategies;

    public MovingStrategies(final List<MovingStrategy> strategies) {
        this.strategies = strategies;
    }

    public MovingStrategy findStrategy(final Position source, final Position target) {
        return strategies.stream()
                .filter(strategy -> strategy.movable(source, target))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("행마법상 이동 불가능한 지역입니다."));
    }
}
