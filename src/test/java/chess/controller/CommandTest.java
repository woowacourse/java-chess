package chess.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class CommandTest {

    @Test
    void 입력_값이_START가_아니라면_예외를_던진다() {
        // expect
        assertThatThrownBy(() -> Command.createStart("end"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("start를 입력해주세요.");
    }

    @Test
    void 입력_값이_START_라면_정상_반환한다() {
        // given
        final Command command = Command.createStart("start");

        // expect
        assertThat(command).isEqualTo(Command.START);
    }

    @Test
    void 입력_값이_MOVE_또는_END가_아니라면_예외를_던진다() {
        // expect
        assertThatThrownBy(() -> Command.createPlayOrEnd("start"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("move 또는 end를 입력해주세요.");
    }

    @Test
    void 입력_값이_MOVE_또는_END_라면_정상_반환한다() {
        // given
        final Command command = Command.createPlayOrEnd("move");

        // expect
        assertThat(command).isEqualTo(Command.MOVE);
    }
}
