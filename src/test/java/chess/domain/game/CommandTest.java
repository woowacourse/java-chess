package chess.domain.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CommandTest {

    @Test
    void from_메소드는_잘못된_입력_시_예외() {
        //when & then
        Assertions.assertThatThrownBy(() -> Command.from("sttttartttt"))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("올바른 명령어를 입력하세요.");
    }

    @Test
    void validateCommandSize_메소드는_1번째_인자가_기대하는_사이즈와_다르면_예외() {
        //when & then
        Assertions.assertThatThrownBy(() -> Command.validateCommandSize(2, Command.MOVE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 명령어를 입력하세요.");
    }
}
