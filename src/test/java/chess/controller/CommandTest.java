package chess.controller;

import static chess.domain.board.Rank.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.in;

import chess.domain.board.Rank;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class CommandTest {

    @Test
    void 문자열이_두_개의_Command_에_속하지_않으면_예외가_발생한다() {
        // expect
        assertThatThrownBy(() -> Command.getValidate("end", Command.MOVE, Command.START))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("move 또는 start 를 입력해주세요.");
    }

    @ParameterizedTest(name = "문자열이 두 개의 Command 중 하나의 값이라면 그 Command를 반환한다 문자열: {0}, firstCommand: {1}, secondCommand: {2}, 생성 Command: {3}")
    @CsvSource({"move, MOVE, START, MOVE", "move, MOVE, START, MOVE"})
    void 문자열이_두_개의_Command_중_하나의_값이라면_그_Command를_반환한다(
            final String inputCommand, final Command firstCommand, final Command secondCommand, final Command generatedCommand
    ) {
        // expect
        assertThat(Command.getValidate(inputCommand, firstCommand, secondCommand)).isEqualTo(generatedCommand);
    }
}
