package chess.controller.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class MainCommandTest {

    @Test
    void 올바른_입력값이_아니라면_예외를_던진다() {
        // given
        final String invalidCommand = "invalid";

        // expect
        assertThatThrownBy(() -> MainCommand.from(invalidCommand))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 명령어를 입력해주세요.");
    }

    @Test
    void Command가_정상_반환된다() {
        // given
        final String validCommand = "START";

        // when
        final MainCommand command = MainCommand.from(validCommand);

        // expect
        assertThat(command).isEqualTo(MainCommand.START);
    }
}
