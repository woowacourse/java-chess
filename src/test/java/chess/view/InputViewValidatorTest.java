package chess.view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewValidatorTest {

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"1", "2", "3", "a2 b2"})
    @DisplayName("잘못된 게임 명령어 입력이 들어오면 예외를 발생시킨다.")
    void throws_exception_when_input_invalid_command(final String input) {
        // given
        InputViewValidator inputViewValidator = new InputViewValidator();

        // when & then
        assertThatThrownBy(() -> inputViewValidator.validate(input))
            .isInstanceOf(IllegalArgumentException.class);

    }

}
