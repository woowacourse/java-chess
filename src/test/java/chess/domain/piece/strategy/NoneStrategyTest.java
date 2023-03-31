package chess.domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NoneStrategyTest {

    private MoveStrategy strategy;

    @BeforeEach
    void setUpStrategy() {
        strategy = NoneStrategy.instance();
    }

    @DisplayName("움직임 판별 메서드 호출시 예외가 발생한다")
    @Test
    void canMove_throws() {
        assertThatThrownBy(() -> strategy.canMove(null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("해당 이동 전략을 불러올 수 없습니다.");
    }
}
