package chess.command;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CommandTest {
    @ParameterizedTest
    @MethodSource("rightCommandUsage")
    void of_존재하는_명령어일_경우(String command, Command expected) {
        assertThat(Command.of(command)).isEqualTo(expected);
    }

    private static Stream<Arguments> rightCommandUsage() {
        return Stream.of(
                Arguments.of("start", Command.START),
                Arguments.of("end", Command.END)
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void of_명령어가_비어있는_경우_예외처리(String command) {
        assertThatThrownBy(() -> Command.of(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Command.NOT_FOUND_MESSAGE);
    }

    @ParameterizedTest
    @CsvSource(value = {"히히", "무늬"})
    void of_존재하지_않는_명령_예외처리(String command) {
        assertThatThrownBy(() -> Command.of(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Command.NOT_FOUND_MESSAGE);
    }
}
