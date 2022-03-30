package chess.domain.game;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("게임이 대기 상태이면 True를 반환")
    void isWaiting() {
        ChessGame chessGame = new ChessGame();

        assertThat(chessGame.isWaiting()).isTrue();
    }

    @Test
    @DisplayName("게임이 대기 상태가 아니면 False를 반환")
    void isNotWaiting() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();

        assertThat(chessGame.isWaiting()).isFalse();
    }

    @Test
    @DisplayName("게임이 종료 상태이면 True를 반환")
    void isFinished() {
        ChessGame chessGame = new ChessGame();
        chessGame.end();

        assertThat(chessGame.isFinish()).isTrue();
    }
}