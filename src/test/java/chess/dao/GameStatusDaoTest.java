package chess.dao;

import chess.dao.fake.FakeGameStatusDao;
import chess.domain.GameStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameStatusDaoTest {

    private GameStatusDao gameStatusDao;

    @BeforeEach
    void init() {
        gameStatusDao = new FakeGameStatusDao();
        gameStatusDao.reset();
    }

    @DisplayName("초기 값을 확인한다.")
    @Test
    void getStatus() {
        // given
        GameStatus initStatus = GameStatus.READY;

        // then
        Assertions.assertThat(gameStatusDao.getStatus()).isEqualTo(initStatus.toString());
    }

    @DisplayName("상태를 변경 후 변경 값을 확인한다.")
    @Test
    void update() {
        // given
        GameStatus initStatus = GameStatus.READY;
        GameStatus nextStatus = GameStatus.PLAYING;
        //when
        gameStatusDao.update(initStatus.toString(), nextStatus.toString());
        // then
        Assertions.assertThat(gameStatusDao.getStatus()).isEqualTo(nextStatus.toString());
    }

    @DisplayName("리셋을 확인한다.")
    @Test
    void reset() {
        // given
        GameStatus initStatus = GameStatus.READY;
        GameStatus nextStatus = GameStatus.PLAYING;
        //when
        gameStatusDao.update(initStatus.toString(), nextStatus.toString());
        gameStatusDao.reset();
        // then
        Assertions.assertThat(gameStatusDao.getStatus()).isEqualTo(initStatus.toString());
    }
}
