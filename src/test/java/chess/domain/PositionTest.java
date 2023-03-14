package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("rank 또는 file이 0~7이 아니면 예외가 발생한다.")
    void validateRange() {
        assertThatThrownBy(() -> new Position(-1, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 잘못된 위치입니다.");
    }

    @Test
    @DisplayName("새로운 위치를 반환한다.")
    void moveBy() {
        Position position = new Position(1, 2);
        assertThat(position.moveBy(1, 1)).isEqualTo(new Position(2, 3));
    }

    @Test
    @DisplayName("변경된 위치의 rank 또는 file이 0~7이 아니면 예외가 발생한다.")
    void validateAddedPositionRange() {
        assertThatThrownBy(() -> new Position(7, 7).moveBy(1, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 잘못된 위치입니다.");
    }
}
