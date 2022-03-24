package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {

    @DisplayName("유효하지 않은 커맨드")
    @ParameterizedTest
    @ValueSource(strings = {"show", "pause"})
    void invalid(final String invalidCommand) {
        final var ChessGame = new ChessGame();

        assertThatThrownBy(() -> Command.execute(new String[]{invalidCommand}, ChessGame))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 명령어 입니다.");
    }

    @Test
    @DisplayName("게임이 시작했을 때 start 명령어를 할 경우 예외 처리")
    void start_exception() {
        ChessGame chessGame = new ChessGame();
        Command.execute(new String[]{"start"}, chessGame);

        assertThatThrownBy(() -> Command.execute(new String[]{"start"}, chessGame))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("게임이 이미 시작되었습니다.");
    }
}
