package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MovementPatternTest {

    @Test
    @DisplayName("horizon, vertical 을 입력받아 MovementPattern 을 찾는다")
    void of() {
        assertThat(MovementPattern.of(1, 0)).isEqualTo(MovementPattern.EAST);
    }

    @Test
    @DisplayName("존재하지 않는 MovementPattern 일 경우 에러 발생")
    void of_NotExistPattern() {
        assertThatThrownBy(() -> MovementPattern.of(2, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[Error] 존재하지 않는 이동 전략입니다.");
    }
}