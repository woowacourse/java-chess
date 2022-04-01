package chess.domain;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PositionTest {
    @Test
    @DisplayName("포지션 입력 값이 2글자가 아니면 예외가 발생한다.")
    void validateLength() {
        String input = "a12";
        assertThatThrownBy(() -> Position.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("올바른 포지션 값이 아닙니다.");
    }
}
