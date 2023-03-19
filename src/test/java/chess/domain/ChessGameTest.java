package chess.domain;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.game.ChessGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessGameTest {

    @Test
    @DisplayName("게임을 시작한 후에 다시 시작할 수 없다.")
    void chessGame_startAndStart_exception() {
        //given
        final var chessGame = new ChessGame();
        chessGame.startGame();

        assertThatThrownBy(() -> chessGame.startGame())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("시작하기 전에 이동 명령을 내릴 수 없다.")
    void playTurn_beforeStart_exception() {
        final var chessGame = new ChessGame();
        Position source = new Position(File.A, Rank.SEVEN);
        Position target = new Position(File.A, Rank.SEVEN);

        assertThatThrownBy(() -> chessGame.playTurn(source, target))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("end를 호출하면 onGoing이 false가 된다.")
    void end_onGoing_false() {
        //given
        final var chessGame = new ChessGame();
        //when
        chessGame.end();
        //then
        assertThat(chessGame.isOnGoing()).isFalse();
    }

    @Test
    @DisplayName("end가 호출되기전엔 onGoing이 true이다")
    void beforeEnd_onGoing_true() {
        final var chessGame = new ChessGame();
        assertThat(chessGame.isOnGoing()).isTrue();
    }

    @Test
    @DisplayName("end가 호출되기전엔 onGoing이 true이다2")
    void beforeEnd_onGoing_true2() {
        final var chessGame = new ChessGame();
        chessGame.startGame();
        assertThat(chessGame.isOnGoing()).isTrue();
    }
}
