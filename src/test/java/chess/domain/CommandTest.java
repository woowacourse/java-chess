package chess.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    @ParameterizedTest
    @CsvSource(value = {"start", "end", "status", "move"})
    void find(String command) {
        assertThat(Command.find(command)).isInstanceOf(Command.class);
    }

    @Test
    void notFind() {
        assertThatThrownBy(() -> {
            Command.find("yellow");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}