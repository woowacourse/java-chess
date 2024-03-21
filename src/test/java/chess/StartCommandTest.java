package chess;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.StartCommand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class StartCommandTest {

    @DisplayName("공백을 입력하면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void BlankInputThrowException(final String value) {
        Assertions.assertThatThrownBy(() -> StartCommand.from(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 명령어입니다.");
    }

    @DisplayName("null을 입력하면 예외를 발생시킨다.")
    @ParameterizedTest
    @NullSource
    void nullInputThrowException(final String value) {
        Assertions.assertThatThrownBy(() -> StartCommand.from(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 명령어입니다.");
    }

    @DisplayName("지정된 명령어가 아니면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"st", "END", "go"})
    void test(final String value) {
        Assertions.assertThatThrownBy(() -> StartCommand.from(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력은 start, end로 해야 합니다.");
    }

    @DisplayName("올바른 입력에 대해 명령어를 생성한다.")
    @CsvSource(value = {"start, START", "end, END"})
    @ParameterizedTest
    void create(final String value, final StartCommand expected) {
        // given
        final StartCommand command = StartCommand.from(value);

        // when & then
        assertThat(command).isEqualTo(expected);
    }
}
