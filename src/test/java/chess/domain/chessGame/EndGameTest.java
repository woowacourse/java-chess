package chess.domain.chessGame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.location.Column;
import chess.domain.location.Location;
import chess.domain.location.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndGameTest {
    public static final ChessGame END_GAME = new EndGame();
    public static final Location B1 = new Location(Column.B, Row.ONE);
    public static final Location B2 = new Location(Column.B, Row.TWO);

    @DisplayName("이미 종료된 게임은 시작할 수 없다.")
    @Test
    void startGameTest() {
        assertThatThrownBy(() -> END_GAME.startGame(() -> true))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 게임이 종료되었습니다.");
    }

    @DisplayName("이미 종료된 게임은 종료할 수 없다.")
    @Test
    void endGameTest() {
        assertThatThrownBy(END_GAME::endGame)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 게임이 종료되었습니다.");
    }

    @DisplayName("이미 종료된 게임에서 기물을 이동시킬 수 없다.")
    @Test
    void moveGameTest() {
        assertThatThrownBy(() -> END_GAME.move(B1, B2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 게임이 종료되었습니다.");
    }

    @DisplayName("종료된 게임에서 종료된 상태를 확인할 수 있다.")
    @Test
    void checkStateTest() {
        assertThat(END_GAME.isNotEnd()).isFalse();
    }

    @DisplayName("이미 종료된 게임에서는 보드를 확인할 수 없다.")
    @Test
    void getBoardTest() {
        assertThatThrownBy(END_GAME::getBoard)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 게임이 종료되었습니다.");
    }
}
