package chess.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {

    @ParameterizedTest
    @CsvSource(value = {"s t a r t", "test", "star"})
    @DisplayName("명령이 올바른 형식으로 들어오지 않았을 때 예외를 발생시킨다")
    void validate(String text) {
        // given
        String input = text;

        // then
        assertThatThrownBy(() -> Command.validateCommand(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 명령입니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"move a2", "move", "move   b2"})
    @DisplayName("이동 명령이 올바른 형식으로 들어오지 않았을 때 예외를 발생시킨다")
    void getFromPosition(String text) {
        //given
        String input = text;
        // then
        assertThatThrownBy(() -> Command.getFromPosition(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 명령입니다.");
    }
}
