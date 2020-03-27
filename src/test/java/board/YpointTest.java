package board;

import chess.domain.board.Ypoint;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class YpointTest {

    @ParameterizedTest
    @DisplayName("필드 변수로 갖고 있는 문자를 입력받으면 enum 클래스를 정상 반환해야 함")
    @ValueSource(strings = {"8", "7", "6", "5", "4", "3", "2", "1"})
    void inputVarCharThenReturnYpointClassTest(char input) {
        Assertions.assertThat(Ypoint.of(input) != null).isTrue();
    }

    @ParameterizedTest
    @DisplayName("필드 변수로 갖고 있는 숫자를 입력받으면 enum 클래스를 정상 반환해야 함")
    @ValueSource(strings = {"8", "7", "6", "5", "4", "3", "2", "1"})
    void inputVarNumThenReturnYpointClassTest(int input) {
        Assertions.assertThat(Ypoint.of(input) != null).isTrue();
    }

    @ParameterizedTest
    @DisplayName("필드 변수에 없는 문자를 입력받으면 예외가 발생해야 함")
    @ValueSource(strings = {"9", "0", "a", "ㄱ", "-"})
    void inputNotVarCharThenReturnException(char input) {
        Assertions.assertThatThrownBy(() -> Ypoint.of(input)).hasMessage("존재하지 않는 세로행입니다.");
    }

    @ParameterizedTest
    @DisplayName("필드 변수에 없는 숫자를 입력받으면 예외가 발생해야 함")
    @ValueSource(strings = {"10", "9", "0", "-1"})
    void inputNotVarNumThenReturnException(int input) {
        Assertions.assertThatThrownBy(() -> Ypoint.of(input)).hasMessage("존재하지 않는 세로행입니다.");
    }
}
