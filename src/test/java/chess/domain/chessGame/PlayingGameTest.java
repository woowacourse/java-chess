package chess.domain.chessGame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.location.Column;
import chess.domain.location.Location;
import chess.domain.location.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayingGameTest {


    public static final ChessGame PLAYING_GAME = new PlayingGame();
    public static final Location B2 = new Location(Column.B, Row.TWO);
    public static final Location B3 = new Location(Column.B, Row.THREE);

    @DisplayName("진행중인 게임은 게임을 시작할 수 없다.")
    @Test
    void startGameTest() {
        assertThatThrownBy(PLAYING_GAME::startGame)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 진행중입니다. 게임을 재시작 할 수 없습니다.");
    }

    @DisplayName("진행중인 게임은 게임을 종료할 수 있다.")
    @Test
    void endGameTest() {
        ChessGame endGame = PLAYING_GAME.endGame();
        assertThat(endGame).isInstanceOf(EndGame.class);
    }

    @DisplayName("진행중인 게임에서 기물을 이동시킬 수 있다.")
    @Test
    void moveGameTest() {
        assertThatNoException()
                .isThrownBy(() -> PLAYING_GAME.move(B2, B3));
    }

    @DisplayName("진행중인 게임에서 종료된 상태를 확인할 수 있다.")
    @Test
    void checkStateTest() {
        assertThat(PLAYING_GAME.isNotEnd()).isTrue();
    }

    @DisplayName("진행중인 게임에서는 보드를 확인할 수 없다.")
    @Test
    void getBoardTest() {
        assertThatNoException()
                .isThrownBy(PLAYING_GAME::getBoard);
    }
}
