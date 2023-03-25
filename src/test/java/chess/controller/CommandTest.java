package chess.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.controller.Command;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class CommandTest {

    @Test
    void 시작시_move_를_입력받으면_예외가_발생한다() {
        // expect
        assertThatThrownBy(() -> Command.createInitCommand("move"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("start 또는 end 를 입력해주세요.");
    }

    @Test
    void 게임_진행중_start_를_입력받으면_예외가_발생한다() {
        // expect
        assertThatThrownBy(() -> Command.createPlayingCommand("start"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("move, status, end 중 하나를 입력해주세요.");
    }

    @ParameterizedTest(name = "start 혹은 end를 입력받을 때만 해당 Command를 반환한다 문자열: {0}, Command: {1}")
    @CsvSource({"start, START", "end, END"})
    void start_혹은_end_를_입력받으면_그_Command_를_반환한다(final String input, final Command targetCommand) {
        // expect
        assertThat(Command.createInitCommand(input)).isEqualTo(targetCommand);
    }

    @ParameterizedTest(name = "move, status, end 중 하나만을 입력받을 때만 해당 Command를 반환한다 문자열: {0}, Command: {1}")
    @CsvSource({"move, MOVE", "status, STATUS","end, END"})
    void move_status_end_를_입력받을_때만_해당_Command_를_반환한다(final String input, final Command targetCommand) {
        // expect
        assertThat(Command.createPlayingCommand(input)).isEqualTo(targetCommand);
    }

    @ParameterizedTest(name = "이동 명령의 크기가 1 혹은 3이 아니라면 예외가 발생한다")
    @MethodSource("CommandsSource")
    void 실행_명령의_크기가_1_혹은_3이_아니라면_예외가_발생한다(final List<String> inputCommands) {
        // expect
        assertThatThrownBy(() -> Command.validateMoveCommandForm(inputCommands))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("명령어 형식을 확인해주세요.");
    }

    static Stream<Arguments> CommandsSource() {
        return Stream.of(
                Arguments.of(List.of("move", "b2")),
                Arguments.of(List.of("move", "b2", "b5", "b8")),
                Arguments.of(List.of("move", "b2", "b5", "b8", "c6")),
                Arguments.of(List.of("move", "b2", "b5", "b8", "f2", "f7"))
        );
    }

    @Test
    void 게임_실행_중_명령은_크기가_1_혹은_3이면_예외가_발생하지_않는다() {
        Command.validateMoveCommandForm(List.of("move", "b7", "b8"));
        Command.validateMoveCommandForm(List.of("end"));
    }
}
