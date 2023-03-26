package chess.controller.room;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RoomCommandTest {

    @Test
    void 올바른_입력값이_아니라면_예외를_던진다() {
        // given
        final List<String> invalidCommands = List.of("invalid");

        // expect
        assertThatThrownBy(() -> RoomCommand.from(invalidCommands))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 명령어를 입력해주세요.");
    }

    @Test
    void Command가_정상_반환된다() {
        // given
        final List<String> validCommand = List.of("history");

        // when
        final RoomCommand command = RoomCommand.from(validCommand);

        // expect
        assertThat(command).isEqualTo(RoomCommand.HISTORY);
    }

    @Test
    void 명령어가_올바른_길이가_아니라면_예외를_던진다() {
        // given
        final List<String> invalidCommands = List.of("join", "1", "3");
        final RoomCommand command = RoomCommand.from(invalidCommands);

        // expect
        assertThatThrownBy(() -> command.validateCommandsSize(invalidCommands))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 명령어를 입력해주세요.");
    }
}
