package chess.domain.controller;

import chess.controller.Command;
import chess.domain.ChessGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {

    @DisplayName("유효하지 않은 커맨드")
    @ParameterizedTest
    @ValueSource(strings = {"show", "pause"})
    void invalidCommandException(final String invalidCommand) {
        ChessGame ChessGame = new ChessGame();

        assertThatThrownBy(() -> Command.execute(invalidCommand, ChessGame))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 명령어 입니다.");
    }

    @Test
    @DisplayName("게임이 시작했을 때 start 명령어를 할 경우 예외 처리")
    void alreadyStartedException() {
        ChessGame chessGame = new ChessGame();
        Command.execute("start", chessGame);

        assertThatThrownBy(() -> Command.execute("start", chessGame))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 이미 시작되었습니다.");
    }

    @Test
    @DisplayName("ready 상태일 때 move 명령어를 할 경우 예외 처리")
    void moveBeforeStartException() {
        ChessGame chessGame = new ChessGame();

        assertThatThrownBy(() -> Command.execute("move a1 a2", chessGame))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임을 시작해주세요.");
    }

    @DisplayName("move 명령어 입력 시 입력 형식에 맞지 않을 경우 예외 처리")
    @ParameterizedTest
    @ValueSource(strings = {"move", "move a1", "move a1 a2 a3"})
    void onlyMoveCommandException(String invalidCommand) {
        ChessGame chessGame = new ChessGame();
        Command.execute("start", chessGame);

        assertThatThrownBy(() -> Command.execute(invalidCommand, chessGame))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("'move source위치 target위치' 의 형식으로 입력해주세요.");
    }

    @Test
    @DisplayName("게임 시작 전 status 명령어 입력 시 예외 처리")
    void beforeEndStatusException() {
        ChessGame chessGame = new ChessGame();

        assertThatThrownBy(() -> Command.execute("status", chessGame))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임을 시작해주세요.");
    }

    @Test
    @DisplayName("white turn 상태일 때 status 명령어 입력 시 예외 처리")
    void whiteTurnStatusException() {
        ChessGame chessGame = new ChessGame();
        Command.execute("start", chessGame);

        assertThatThrownBy(() -> Command.execute("status", chessGame))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 종료된 이후 점수 조회가 가능합니다.");
    }

    @Test
    @DisplayName("black turn 상태일 때 status 명령어 입력 시 예외 처리")
    void blackTurnStatusException() {
        ChessGame chessGame = new ChessGame();
        Command.execute("start", chessGame);
        Command.execute("move a2 a3", chessGame);

        assertThatThrownBy(() -> Command.execute("status", chessGame))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 종료된 이후 점수 조회가 가능합니다.");
    }

    @Test
    @DisplayName("end 상태일 때 status 명령어 입력 시 예외 처리")
    void endStatusException() {
        ChessGame chessGame = new ChessGame();
        Command.execute("end", chessGame);

        assertThatThrownBy(() -> Command.execute("status", chessGame))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 종료됐습니다.");
    }
}
