package chess.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CommandTest {

    @ParameterizedTest
    @DisplayName("#of() : return Command that matches value")
    @MethodSource({"getCasesForOf"})
    void of(String value, Command expected) {
        assertThat(Command.of(value)).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForOf() {
        return Stream.of(
                Arguments.of("start", Command.START),
                Arguments.of("end", Command.END),
                Arguments.of("move", Command.MOVE),
                Arguments.of("status", Command.STATUS)
        );
    }
}