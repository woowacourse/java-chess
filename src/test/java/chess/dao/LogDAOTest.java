package chess.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class LogDAOTest {
    private final LogDAO logDAO = new LogDAO(new ConnectDB());

    @Test
    @DisplayName("로그 저장")
    void saveLog() throws SQLException {
        logDAO.createLog("1", "b2", "b4");
    }
}
