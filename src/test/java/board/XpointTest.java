package board;

import chess.domain.board.Xpoint;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class XpointTest {

    @ParameterizedTest
    @DisplayName("필드 변수로 갖고 있는 문자를 입력받으면 enum 클래스를 정상 반환해야 함")
    @ValueSource(strings = {"a", "b", "c", "d", "e", "f", "g", "h"})
    void inputVarCharThenReturnXpointClassTest(char input) {
        Assertions.assertThat(Xpoint.of(input) != null).isTrue();
    }

    @ParameterizedTest
    @DisplayName("필드 변수로 갖고 있는 숫자를 입력받으면 enum 클래스를 정상 반환해야 함")
    @ValueSource(strings = {"1", "2", "3", "4", "5", "6", "7", "8"})
    void inputVarNumThenReturnXpointClassTest(int input) {
        Assertions.assertThat(Xpoint.of(input) != null).isTrue();
    }

    @ParameterizedTest
    @DisplayName("필드 변수에 없는 문자를 입력받으면 예외가 발생해야 함")
    @ValueSource(strings = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "i", "L", "m"})
    void inputNotVarCharThenReturnException(char input) {
        Assertions.assertThatThrownBy(() -> Xpoint.of(input)).hasMessage("존재하지 않는 가로열입니다.");
    }

    @ParameterizedTest
    @DisplayName("필드 변수에 없는 숫자를 입력받으면 예외가 발생해야 함")
    @ValueSource(strings = {"-2", "-1", "0", "9", "10", "11", "12"})
    void inputNotVarNumThenReturnException(int input) {
        Assertions.assertThatThrownBy(() -> Xpoint.of(input)).hasMessage("존재하지 않는 가로열입니다.");
    }
}
