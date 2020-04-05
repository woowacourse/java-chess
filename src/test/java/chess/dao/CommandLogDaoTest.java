package chess.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandLogDaoTest {
    private CommandLogDao commandLogDao;

    @BeforeEach
    public void setUp() {
        commandLogDao = new CommandLogDao();
    }

    @Test
    void connection() {
        Connection connection = commandLogDao.getConnection();
        assertNotNull(connection);
    }
}
