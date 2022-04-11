package chess;

import chess.controller.Command;
import chess.domain.ChessGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {

    private final ChessGame chessGame = new ChessGame();

    @DisplayName("유효하지 않은 커맨드 입력시 예외발생")
    @ParameterizedTest
    @ValueSource(strings = {"show", "pause"})
    void invalid(final String invalidCommand) {
        assertThatThrownBy(() -> Command.execute(invalidCommand, chessGame))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 명령어 입니다.");
    }

    @DisplayName("move 입력시 위치값 개수가 올바르지 않은경우 예외발생")
    @ParameterizedTest
    @ValueSource(strings = {"move", "move a1", "move a1 a2 a3"})
    void onlyMoveCommand(final String invalidCommand) {
        assertThatThrownBy(() -> Command.execute(invalidCommand, chessGame))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시작 위치와 도착 위치를 입력해주세요.");
    }


    @Test
    @DisplayName("move 명령어 입력 시 위치값이 중복된 경우 예외 발생")
    void onlyStartPosition() {
        assertThatThrownBy(() -> Command.execute("move a1 a1", chessGame))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 위치값은 사용될 수 없습니다.");
    }

    @Test
    @DisplayName("move 입력시 위치값 개수가 올바른 경우")
    void onlyMoveCommand() {
        Command.execute("start", chessGame);

        assertThatCode(() -> Command.execute("move a2 a3", chessGame))
                .doesNotThrowAnyException();
    }
}
