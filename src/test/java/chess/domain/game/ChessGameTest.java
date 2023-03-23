package chess.domain.game;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {
    
    @Test
    @DisplayName("체스 게임을 생성한다.")
    void createChessGame() {
        ChessGame chessGame = new ChessGame();
        Assertions.assertThat(chessGame.isNotRunning()).isTrue();
        Assertions.assertThat(chessGame.isNotEnd()).isTrue();
    }
    
    @Test
    @DisplayName("체스 게임을 시작한다.")
    void startChessGame() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        Assertions.assertThat(chessGame.isNotReady()).isTrue();
        Assertions.assertThat(chessGame.isNotEnd()).isTrue();
    }
    
    @Test
    @DisplayName("체스 게임을 종료한다.")
    void endChessGame() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        chessGame.end();
        Assertions.assertThat(chessGame.isNotRunning()).isTrue();
        Assertions.assertThat(chessGame.isNotReady()).isTrue();
    }
    
    @Test
    @DisplayName("체스 게임 시작 전에는 기물을 움직일 수 없다.")
    void moveBeforeStart() {
        ChessGame chessGame = new ChessGame();
        Assertions.assertThatThrownBy(() -> chessGame.move(Position.from("a2"), Position.from("a3")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(ChessGame.GAME_HAS_NOT_STARTED);
    }
    
    @Test
    @DisplayName("체스 게임이 시작 전에는 게임을 끝낼 수 없다.")
    void endBeforeStart() {
        ChessGame chessGame = new ChessGame();
        Assertions.assertThatThrownBy(chessGame::end)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(ChessGame.GAME_HAS_NOT_STARTED);
    }
    
    @Test
    @DisplayName("체스 게임이 시작 전에는 게임 상태를 확인할 수 없다.")
    void getStatusBeforeStart() {
        ChessGame chessGame = new ChessGame();
        Assertions.assertThatThrownBy(chessGame::status)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(ChessGame.GAME_HAS_NOT_STARTED);
    }
    
    @Test
    @DisplayName("체스 게임이 시작한 후에는 다시 시작할 수 없다.")
    void startAfterStart() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        Assertions.assertThatThrownBy(chessGame::start)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(ChessGame.GAME_HAS_ALREADY_STARTED);
    }
    
    @Test
    @DisplayName("킹이 잡히면 게임이 종료된다.")
    void endWhenKingCaught() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        chessGame.move(Position.from("e2"), Position.from("e4"));
        chessGame.move(Position.from("e7"), Position.from("e5"));
        chessGame.move(Position.from("d1"), Position.from("h5"));
        chessGame.move(Position.from("f7"), Position.from("f5"));
        chessGame.move(Position.from("h5"), Position.from("e8"));
        Assertions.assertThat(chessGame.isNotRunning()).isTrue();
        Assertions.assertThat(chessGame.isNotReady()).isTrue();
    }
}