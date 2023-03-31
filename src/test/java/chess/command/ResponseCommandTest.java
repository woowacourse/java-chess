package chess.command;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ResponseCommandTest {

    @ParameterizedTest(name = "입력 값이 y 이면 true를 반환한다.")
    @CsvSource({"y,true", "n,false"})
    void isYes(String input, boolean isYes) {
        assertThat(ResponseCommand.isYes(input)).isEqualTo(isYes);
    }

    @ParameterizedTest(name = "입력 값이 y, n가 아니면 예외를 발생시킨다.")
    @ValueSource(strings = {"q", "e"})
    void isYes_fail(String input) {
        assertThatThrownBy(() -> ResponseCommand.isYes(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y 또는 n로 응답해주세요.");
    }
}
