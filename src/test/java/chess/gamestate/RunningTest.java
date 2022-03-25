package chess.gamestate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RunningTest {

    @ParameterizedTest
    @ValueSource(strings = {"start", "move1", "end1"})
    @DisplayName("현재 상태에서 진행 가능한 커맨드가 아니면 예외발생")
    void runException(String inputLine) {
        GameState gameState = new WhiteRunning(ChessBoard.createNewChessBoard());
        assertThatThrownBy(() -> gameState.run(inputLine))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임 진행상태에서 불가능한 명령어입니다.");
    }

    @Test
    @DisplayName("move커맨드 입력 시 다른 색상 상태로 변경")
    void whiteRunningMove() {
        GameState gameState = new WhiteRunning(ChessBoard.createNewChessBoard()).run("move a2 a4");
        assertThat(gameState).isInstanceOf(BlackRunning.class);
    }

    @Test
    @DisplayName("move커맨드 입력 시 다른 색상 상태로 변경")
    void BlackRunningMove() {
        GameState gameState = new BlackRunning(ChessBoard.createNewChessBoard()).run("move a7 a5");
        assertThat(gameState).isInstanceOf(WhiteRunning.class);
    }

    @Test
    @DisplayName("end커맨드 입력 시 End 상태로 변경")
    void runToEnd() {
        GameState gameState = new WhiteRunning(ChessBoard.createNewChessBoard()).run("end");
        assertThat(gameState).isInstanceOf(End.class);
    }
}
