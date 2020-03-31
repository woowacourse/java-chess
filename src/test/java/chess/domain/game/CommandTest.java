package chess.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.game.Command.END;
import static org.assertj.core.api.Assertions.assertThat;

public class CommandTest {
    @Test
    @DisplayName("isExistCommand 테스트 - 존재하는 커맨드일 때")
    void isExistCommand() {
        assertThat(Command.isExistCommand("start")).isTrue();
    }

    @Test
    @DisplayName("isExistCommand 테스트 - 존재하지 않는 커맨드일 때")
    void isExistCommand_NotExitstCommand_ThrowException() {
        assertThat(Command.isExistCommand("시작")).isFalse();
    }

    @Test
    @DisplayName("equals 테스트")
    void equals() {
        assertThat(END.equals("end")).isTrue();
    }
}
