package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameTest {

    @Test
    @DisplayName("점수 반환 확인")
    void checkScore() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();

        Map<String, Object> scores = chessGame.createScore(new HashMap<>());
        Score score = (Score) scores.get(Color.WHITE.name());

        assertThat(score.getScore()).isEqualTo(38);
    }

    @Test
    @DisplayName("ready: 체스 게임이 끝난 상태인지 확인")
    void checkReadyFinish() {
        ChessGame chessGame = new ChessGame();

        assertThat(chessGame.isFinished()).isFalse();
    }

    @Test
    @DisplayName("play: 체스 게임이 끝난 상태인지 확인")
    void checkPlayFinish() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();

        assertThat(chessGame.isFinished()).isFalse();
    }

    @Test
    @DisplayName("finish: 체스 게임이 끝난 상태인지 확인")
    void checkFinish() {
        ChessGame chessGame = new ChessGame();
        chessGame.end();

        assertThat(chessGame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("turn이 맞는지 확인")
    void checkTurn() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();

        assertThat(chessGame.isRightTurn("white")).isTrue();
    }

    @Test
    @DisplayName("running 상태가 맞는지 확인")
    void checkRunning() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();

        assertThat(chessGame.isRunning()).isTrue();
    }

    @Test
    @DisplayName("find piece가 올바른지 확인")
    void checkRightPiece() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();

        assertThat(chessGame.findPiece("a1")).isInstanceOf(Rook.class);
    }
}
