package chess.controller;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class CommandTest {

    @Test
    void 올바른_커맨드를_입력하지_않으면_예외를_던진다() {
        assertThatThrownBy(() -> Command.from("InvalidCommand"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 커맨드를 입력해주세요.");
    }
}
