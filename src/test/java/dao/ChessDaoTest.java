package dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ChessDaoTest {

    private final ChessGameDao chessGameDao = new ChessGameDao(new TestDbConnectionGenerator());

    @DisplayName("새로운 게임 방을 만든다.")
    @Test
    void newGameTest() {
        assertThatCode(() -> chessGameDao.createRoom())
                .doesNotThrowAnyException();
    }

    @DisplayName("존재하지 않는 pk인 0으로 게임 방을 조회하려고 하면 예외가 발생한다.")
    @Test
    void nonExistGameTest() {
        assertThat(chessGameDao.hasGame(0L)).isFalse();
    }

    @DisplayName("모든 게임 방을 조회한다.")
    @Test
    void findAllGameRoomsTest() {
        assertThatCode(() -> chessGameDao.findAllGameRooms())
                .doesNotThrowAnyException();
    }

}