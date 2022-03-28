package chess.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CommandTest {

    @ParameterizedTest
    @CsvSource(value = {"start", "end", "status", "move a2 a3"})
    @DisplayName("커맨드가 생성된다")
    void from(String value) {
        assertThat(Command.from(value))
                .isInstanceOf(Command.class);
    }

    @Test
    @DisplayName("커맨드가 생성된다")
    void fromError() {
        assertThatThrownBy(() -> Command.from("aa"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
