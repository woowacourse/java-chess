package chess.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
                .hasMessage("move 또는 end 를 입력해주세요.");
    }

    @ParameterizedTest(name = "start 혹은 end를 입력받을 때만 해당 Command를 반환한다 문자열: {0}, Command: {1}")
    @CsvSource({"start, START", "end, END"})
    void start_혹은_end_를_입력받으면_그_Command_를_반환한다(final String input, final Command targetCommand) {
        // expect
        assertThat(Command.createInitCommand(input)).isEqualTo(targetCommand);
    }

    @ParameterizedTest(name = "move 혹은 end를 입력받을 때만 해당 Command를 반환한다 문자열: {0}, Command: {1}")
    @CsvSource({"move, MOVE", "end, END"})
    void move_혹은_end_를_입력받을_때만_해당_Command_를_반환한다(final String input, final Command targetCommand) {
        // expect
        assertThat(Command.createPlayingCommand(input)).isEqualTo(targetCommand);
    }
}
