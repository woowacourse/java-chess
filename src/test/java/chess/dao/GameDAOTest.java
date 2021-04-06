package chess.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GameDAOTest {
    private GameDAO gameDao;

    @BeforeEach
    public void setup() {
        gameDao = new GameDAO();
    }

    @Test
    public void getConnectionTest() {
        Connection con = gameDao.getConnection();
        assertNotNull(con);
    }
}