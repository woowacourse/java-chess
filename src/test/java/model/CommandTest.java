package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import exception.InvalidCommandException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

    @Test
    @DisplayName("올바른 command가 입력되면 예외가 발생하지 않는다.")
    void command() {
        assertAll(
                () -> assertThat(Command.from("start")).isEqualTo(Command.START),
                () -> assertThat(Command.from("end")).isEqualTo(Command.END),
                () -> assertThat(Command.from("move")).isEqualTo(Command.MOVE),
                () -> assertThat(Command.from("a1")).isEqualTo(Command.POSITION),
                () -> assertThat(Command.from("h8")).isEqualTo(Command.POSITION)
        );
    }

    @ParameterizedTest
    @DisplayName("유효하지 않은 command가 입력되면 예외가 발생한다.")
    @ValueSource(strings = {"st", "endd", "a0"})
    void validateValue(String value) {
        assertThatThrownBy(() -> Command.validate(value))
                .isInstanceOf(InvalidCommandException.class);
    }
}
