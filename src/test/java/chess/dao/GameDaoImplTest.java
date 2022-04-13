package chess.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameDaoImplTest {
    private static final String TEST_GAME_ID = "TEST-GAME-ID";
    private GameDaoImpl gameDao;

    @BeforeEach
    void setUp() {
        gameDao = new GameDaoImpl();
    }

    @DisplayName("새로운 게임을 game 테이블에 생성한다.")
    @Test
    void createGame() {
        gameDao.createGame(TEST_GAME_ID);
    }

    @DisplayName("게임을 game 테이블로부터 제거한다.")
    @Test
    void deleteGame() {
        // given & when
        gameDao.createGame(TEST_GAME_ID);

        // then
        gameDao.deleteGame(TEST_GAME_ID);
    }

    @DisplayName("게임의 턴을 흰색으로 변경한다.")
    @Test
    void updateTurnToWhite() {
        // given & when
        gameDao.createGame(TEST_GAME_ID);

        // then
        gameDao.updateTurnToWhite(TEST_GAME_ID);
    }

    @DisplayName("게임의 턴을 검정색으로 변경한다.")
    @Test
    void updateTurnToBlack() {
        // given & when
        gameDao.createGame(TEST_GAME_ID);

        // then
        gameDao.updateTurnToBlack(TEST_GAME_ID);
    }

    @AfterEach
    void tearDown() {
        gameDao.deleteGame(TEST_GAME_ID);
    }
}
