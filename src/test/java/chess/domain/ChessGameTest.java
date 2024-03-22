package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(BoardFactory.createInitialBoard());
    }

    @DisplayName("체스 게임을 시작할 수 있다.")
    @Test
    void start() {
        chessGame.start();

        assertThat(chessGame.isPlaying()).isTrue();
    }

    @DisplayName("체스 게임을 종료할 수 있다.")
    @Test
    void end() {
        chessGame.end();

        assertThat(chessGame.isPlaying()).isFalse();
    }

    @DisplayName("체스 게임을 진행할 수 있다.")
    @Test
    void movePiece() {
        chessGame.start();
        chessGame.movePiece("b2", "b3");

        assertThat(chessGame.isPlaying()).isTrue();
    }
}
