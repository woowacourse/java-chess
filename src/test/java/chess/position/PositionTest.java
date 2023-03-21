package chess.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class PositionTest {

    @ParameterizedTest()
    @ValueSource(strings = {"i1", "a0", "b9", "b0", " ", "asd", "123", "", "ac1", "b78"})
    @DisplayName("move command의 source위치와 target위치가 체스판 좌표 형식에 맞지 않으면 예외처리한다.")
    void shouldFailMismatchPositionRegex(String input) {
        assertThatThrownBy(() -> Position.validatePositionRegex(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동 방향이 체스판 형식에 맞지 않습니다.");
    }

    @ParameterizedTest()
    @ValueSource(strings = {"h1", "a8", "b5", "c4", "a1", "h8", "a8"})
    @DisplayName("move command의 source위치와 target위치가 체스판 좌표 형식에 맞으면 성공한다.")
    void shouldSuccessMatchPositionRegex(String input) {
        assertDoesNotThrow(() -> Position.validatePositionRegex(input));
    }

    @ParameterizedTest()
    @CsvSource(value = {"a1, 1, 1", "a8, 1, 8", "h1, 8, 1", "h8, 8, 8", "c7, 3, 7", "d4, 4, 4", "e3, 5, 3"})
    @DisplayName("문자열로 불러온 포지션과 좌표로 불러온 포지션이 서로 같다.")
    void shouldEqualCommandAndPostion(String command, int horizontal, int vertical) {
        assertThat(Position.of(command)).isEqualTo(Position.initPosition(horizontal, vertical));
    }
}
