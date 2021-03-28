package chess.domain.command;

import chess.domain.ChessGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StatusOnCommandTest {
    private StatusOnCommand statusOnCommand;

    @BeforeEach
    void setUp() {
        statusOnCommand = new StatusOnCommand();
    }

    @DisplayName("status 명령어를 실행한다. - 게임이 아직 실행되지 않았을 때 예외")
    @Test
    void status를_실행() {
        ChessGame chessGame = new ChessGame();
        String[] splitCommand = new String[]{"status"};

        assertThatThrownBy(() -> statusOnCommand.execute(chessGame, splitCommand));
    }

    @DisplayName("체스 보드를 출력하지 않아도 된다.")
    @Test
    void 체스보드_출력_안함() {
        boolean isMustShowBoard = statusOnCommand.isMustShowBoard();

        assertThat(isMustShowBoard).isFalse();
    }

    @DisplayName("체스 보드 상태를 출력해야 한다.")
    @Test
    void 체스보드_상태_출력_함() {
        boolean isMustShowStatus = statusOnCommand.isMustShowStatus();

        assertThat(isMustShowStatus).isTrue();
    }
}
