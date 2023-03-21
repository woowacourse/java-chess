package chess.domain;

import chess.domain.exception.StartCommandException;
import chess.view.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessGameTest {

    private ChessGame chessGame;

    @BeforeEach
    void setup() {
        chessGame = new ChessGame();
    }

    @Test
    @DisplayName("게임 시작 시 start를 입력하면 게임을 실행한다.")
    void start_command_success_when_input_start() {
        assertThatCode(() -> chessGame.start(Command.START))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("게임 시작 시 end를 입력하면 예외를 발생한다.")
    void start_command_fail_when_input_end() {
        assertThatThrownBy(() -> chessGame.start(Command.END))
                .isInstanceOf(StartCommandException.class);
    }

    @Test
    @DisplayName("게임 시작 시 move를 입력하면 예외를 발생한다.")
    void start_command_fail_when_input_move() {
        assertThatThrownBy(() -> chessGame.start(Command.MOVE))
                .isInstanceOf(StartCommandException.class);
    }

    @Test
    @DisplayName("게임 진행 중에 start를 입력하면 게임을 재실행한다.")
    void start_command_success_when_restart() {
        assertThatCode(() -> chessGame.restart())
                .doesNotThrowAnyException();
    }
}