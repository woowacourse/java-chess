package chess.domain;

import chess.domain.position.Column;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
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

    @ParameterizedTest
    @ValueSource(strings = {"a1", "a8", "b4", "d3"})
    @DisplayName("위치 값을 문자열로 입력 받은 후, Position을 조회한다.")
    void from() {
        String input = "a1";
        assertThat(Position.from(input).getPositionToString()).isEqualTo(input);
    }
}
