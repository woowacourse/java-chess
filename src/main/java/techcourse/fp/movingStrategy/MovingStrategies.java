package techcourse.fp.movingStrategy;

import java.util.List;
import techcourse.fp.chess.domain.Position;

public class MovingStrategies {

    private final List<MovingStrategy> strategies;

    public MovingStrategies(final List<MovingStrategy> strategies) {
        this.strategies = strategies;
    }

    public MovingStrategy findStrategy(final Position sourcePosition, final Position targetPosition) {
        return strategies.stream()
                .filter(strategy -> strategy.movable(sourcePosition, targetPosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("행마법상 이동 불가능한 지역입니다."));
    }
}
