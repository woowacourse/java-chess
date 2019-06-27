package chess.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameDaoTest {
    private DataSource dataSource = ChessJdbcConnector.getDataSource("chess_test");
    private GameDao gameDao;

    @BeforeEach
    void setUp() {
        gameDao = GameDao.getInstance(dataSource);
    }

    @Test
    void 게임아이디로_현재_턴_찾기() {
        int gameId = 1;
        assertTrue(gameDao.findTurnByGameId(gameId));
    }

    @Test
    void toggle() {
        boolean currentTurn = gameDao.findTurnByGameId(1);
        gameDao.toggleTurnById(1);
        assertTrue(gameDao.findTurnByGameId(1) ^ currentTurn);
    }

    @Test
    void add() {
        gameDao.createNewGame();
    }
}
