package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {

    @ParameterizedTest()
    @ValueSource(strings = {"i1", "a0", "b9", "b0"})
    @DisplayName("move command의 source위치와 target위치가 체스판 좌표 형식에 맞지 않으면 예외처리한다.")
    void shouldFailMismatchPositionRegex(String input) {
        assertThatThrownBy(() -> Position.validatePositionRegex(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동 방향이 체스판 형식에 맞지 않습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"b1, 2, 1", "c8, 3, 8", "a8, 1, 8", "h1, 8, 1", "h8, 8, 8", "c4, 3, 4", "a1, 1, 1"})
    @DisplayName("체스판 형식에 맞는 위치 정보가 올바르게 숫자로 변환된다.")
    void shouldSuccessConvertCommandToNumber(String input, int x, int y) {
        assertThat(Position.convertCommand(input)).isEqualTo(List.of(x, y));

    }

}
