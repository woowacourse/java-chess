package chess.domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IntersectionStrategyTest {

    private MoveStrategy trueStrategy;
    private MoveStrategy falseStrategy;

    @BeforeEach
    void setUpStrategy() {
        trueStrategy = move -> true;
        falseStrategy = move -> false;
    }

    @DisplayName("거짓인 전략이 하나라도 있으면 거짓이다.")
    @Test
    void FalseStrategyExists_False() {
        MoveStrategy strategy = IntersectionStrategy.of(trueStrategy, trueStrategy, falseStrategy);

        assertThat(strategy.canMove(null)).isFalse();
    }

    @DisplayName("거짓인 전략이 하나도 없으면 참이다.")
    @Test
    void FalseStrategyNotExists_True() {
        MoveStrategy strategy = UnionStrategy.of(trueStrategy, trueStrategy);

        assertThat(strategy.canMove(null)).isTrue();
    }
}
