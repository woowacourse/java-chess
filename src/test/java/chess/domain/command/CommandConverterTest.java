package chess.domain.command;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CommandConverterTest {

    private static final List<String> END_INPUT = List.of("end");
    private static final List<String> START_INPUT = List.of("start");
    private static final List<String> STATUS_INPUT = List.of("status");
    private static final List<String> MOVE_INPUT = List.of("move", "a3", "b3");


    @Test
    void make_start() {
        assertThat(CommandConverter.convertCommand(START_INPUT)).isEqualTo(SingleCommand.START);
    }

    @Test
    void make_end() {
        assertThat(CommandConverter.convertCommand(END_INPUT)).isEqualTo(SingleCommand.END);
    }

    @Test
    void make_status() {
        assertThat(CommandConverter.convertCommand(STATUS_INPUT)).isEqualTo(SingleCommand.STATUS);
    }

    @Test
    void make_move() {
        assertThat(CommandConverter.convertCommand(MOVE_INPUT)).isInstanceOf(MoveCommand.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"star", "asd", "", " ", "!"})
    void invalid_single_command(String input) {
        assertThatThrownBy(() -> CommandConverter.convertCommand(List.of(input))).isInstanceOf(
                IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"move:A1:b1", "mov:a1:b1", "move:a1:B1",
            "move:A1:B1", "move:a9:b1", "move:p3:b3"}, delimiter = ':')
    void invalid_move_command(String prefix, String from, String to) {
        assertThatThrownBy(() -> CommandConverter.convertCommand(List.of(prefix, from, to))).isInstanceOf(
                IllegalArgumentException.class);
    }
}
