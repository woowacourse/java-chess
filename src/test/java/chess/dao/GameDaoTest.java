package chess.dao;

import chess.utils.DBUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameDaoTest {
    private DataSource dataSource = DBUtil.getDataSource();
    private GameDao gameDao;

    @BeforeEach
    void setUp() {
        gameDao = new GameDao(dataSource);
    }

    @Test
    void 게임아이디로_현재_턴_찾기() throws SQLException {
        int gameId = 1;
        assertTrue(gameDao.findTurnByGameId(gameId));
    }

    @Test
    void toggle() throws SQLException {
        boolean currentTurn = gameDao.findTurnByGameId(1);
        gameDao.toggleTurnById(1);
        assertTrue(gameDao.findTurnByGameId(1) ^ currentTurn);
    }

    @Test
    void add() throws SQLException {
        gameDao.add();
    }

    @Test
    void delete() throws SQLException {
        gameDao.deleteById(2);
        assertThrows(SQLException.class, () -> gameDao.findTurnByGameId(2));
    }
}
