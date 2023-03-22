package chess;

import chess.domain.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static chess.domain.Position.findPosition;
import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @ParameterizedTest
    @ValueSource(strings = {"a1", "b1", "c1", "d1", "e1", "h1", "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2"})
    @DisplayName("가로 세로 위치 정보를 문자열로 입력할 때 해당 위치를 가진 Position 객체가 반환된다.")
    void shouldSucceedFindPosition(String position) {

        assertThat(findPosition(position)).isInstanceOf(Position.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a9", "a10", "y1", "z2", "aa2", "b33"})
    @DisplayName("해당 Position 객체를 찾기 위해 잘못된 체스 좌표를 입력했을 때 예외 발생")
    void shouldFailFindPosition(String position) {

        Assertions.assertThatThrownBy(() -> Position.findPosition(position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 Position은 존재하지 않습니다.");
    }

    //    @ParameterizedTest()
//    @ValueSource(strings = {"i1", "a0", "b9", "b0"," ", "asd", "123", "", "ac1", "b78"})
//    @DisplayName("move command의 source위치와 target위치가 체스판 좌표 형식에 맞지 않으면 예외처리한다.")
//    void shouldFailMismatchPositionRegex(String input) {
//        assertThatThrownBy(() -> Position.validatePositionRegex(input))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("[ERROR] 이동 방향이 체스판 형식에 맞지 않습니다.");
//    }
//
//    @ParameterizedTest()
//    @ValueSource(strings = {"h1", "a8", "b5", "c4", "a1", "h8", "a8"})
//    @DisplayName("move command의 source위치와 target위치가 체스판 좌표 형식에 맞으면 성공한다.")
//    void shouldSuccessMatchPositionRegex(String input) {
//        Assertions.assertDoesNotThrow(() -> Position.validatePositionRegex(input));
//    }
//
//
//    @ParameterizedTest
//    @CsvSource(value = {"b1, 2, 1", "c8, 3, 8", "a8, 1, 8", "h1, 8, 1", "h8, 8, 8", "c4, 3, 4", "a1, 1, 1"})
//    @DisplayName("체스판 형식에 맞는 위치 정보가 올바르게 숫자로 변환된다.")
//    void shouldSuccessConvertCommandToNumber(String input, int x, int y) {
//        assertThat(Position.convertCommand(input)).isEqualTo(List.of(x, y));
//
//    }
}
