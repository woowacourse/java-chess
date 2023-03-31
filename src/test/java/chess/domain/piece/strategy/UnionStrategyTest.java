package chess.domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UnionStrategyTest {

    private MoveStrategy trueStrategy;
    private MoveStrategy falseStrategy;

    @BeforeEach
    void setUpStrategy() {
        trueStrategy = move -> true;
        falseStrategy = move -> false;
    }

    @DisplayName("참인 전략이 하나라도 있으면 참이다.")
    @Test
    void TrueStrategyExists_True() {
        MoveStrategy strategy = UnionStrategy.of(trueStrategy, falseStrategy, falseStrategy);

        assertThat(strategy.canMove(null)).isTrue();
    }

    @DisplayName("참인 전략이 하나도 없으면 거짓이다.")
    @Test
    void TrueStrategyNotExists_False() {
        MoveStrategy strategy = UnionStrategy.of(falseStrategy, falseStrategy);

        assertThat(strategy.canMove(null)).isFalse();
    }
}
