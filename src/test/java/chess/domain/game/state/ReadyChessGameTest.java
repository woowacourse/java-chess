package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.PiecesPosition;
import chess.domain.game.ChessCommandType;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyChessGameTest {

    private ChessGame readyGame;

    @BeforeEach
    void setRunningGame() {
        PiecesPosition piecesPosition = new PiecesPosition();
        readyGame = new ReadyChessGame(piecesPosition);
    }

    @Test
    @DisplayName("게임 준비 단계에서는 실행 할 수 없다")
    void isRunnableGameTest() {
        assertThatThrownBy(() -> readyGame.isGameRunnable())
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("게임이 준비 단계인 경우 게임을 시작할 수 있다.")
    void playByCommandStartTest() {
        ChessGameCommand startCommand = new ChessGameCommand(ChessCommandType.START);

        ChessGame chessGame = readyGame.playByCommand(startCommand);

        assertThat(chessGame).isInstanceOf(RunningChessGame.class);
    }


    @Test
    @DisplayName("게임이 준비 단계인 경우 종료 명령은 불가능 하다.")
    void playByCommandEndExceptionTest() {
        ChessGameCommand endCommand = new ChessGameCommand(ChessCommandType.END);

        assertThatThrownBy(() -> readyGame.playByCommand(endCommand))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("게임이 준비 단계인 경우 이동 명령은 불가능 하다.")
    void playByCommandMoveExceptionTest() {
        ChessGameCommand moveCommand = new ChessGameCommand(ChessCommandType.MOVE);

        assertThatThrownBy(() -> readyGame.playByCommand(moveCommand))
                .isInstanceOf(IllegalStateException.class);
    }
}
