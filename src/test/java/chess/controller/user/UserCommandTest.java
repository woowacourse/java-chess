package chess.controller.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class UserCommandTest {

    @Test
    void 올바른_입력값이_아니라면_예외를_던진다() {
        // given
        final List<String> invalidCommands = List.of("invalid");

        // expect
        assertThatThrownBy(() -> UserCommand.from(invalidCommands))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 명령어를 입력해주세요.");
    }

    @Test
    void Command가_정상_반환된다() {
        // given
        final List<String> validCommand = List.of("register");

        // when
        final UserCommand command = UserCommand.from(validCommand);

        // expect
        assertThat(command).isEqualTo(UserCommand.REGISTER);
    }

    @Test
    void 명령어가_올바른_길이가_아니라면_예외를_던진다() {
        // given
        final List<String> invalidCommands = List.of("register", "my", "user");
        final UserCommand command = UserCommand.from(invalidCommands);

        // expect
        assertThatThrownBy(() -> command.validateCommandsSize(invalidCommands))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 명령어를 입력해주세요.");
    }
}
