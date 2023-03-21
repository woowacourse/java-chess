package chess;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.position.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PositionTest {

    @ParameterizedTest()
    @ValueSource(strings = {"i1", "a0", "b9", "b0"," ", "asd", "123", "", "ac1", "b78"})
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
        Assertions.assertDoesNotThrow(() -> Position.validatePositionRegex(input));
    }

}
