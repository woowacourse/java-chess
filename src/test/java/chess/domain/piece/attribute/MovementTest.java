package chess.domain.piece.attribute;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MovementTest {
    @DisplayName("방향을 1개 이상 입력하지 않으면 예외가 발생한다.")
    @Test
    void of() {
        assertThatThrownBy(Movement::of)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동을 위한 방향은 1개 이상입니다.");
    }
}
