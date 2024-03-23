package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

    @DisplayName("공백을 입력하면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void BlankInputThrowException(final String value) {
        Assertions.assertThatThrownBy(() -> Command.fromStartCommand(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("null을 입력하면 예외를 발생시킨다.")
    @ParameterizedTest
    @NullSource
    void nullInputThrowException(final String value) {
        Assertions.assertThatThrownBy(() -> Command.fromStartCommand(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("지정된 명령어가 아니면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"st", "END", "go"})
    void test(final String value) {
        Assertions.assertThatThrownBy(() -> Command.fromStartCommand(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("올바른 입력에 대해 명령어를 생성한다.")
    @CsvSource(value = {"start, START", "end, END"})
    @ParameterizedTest
    void create(final String value, final Command expected) {
        // given
        final Command command = Command.fromStartCommand(value);

        // when & then
        assertThat(command).isEqualTo(expected);
    }
}
