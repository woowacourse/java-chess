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

    @Test
    @DisplayName("해당 방의 모든 움직임 로그 삭제")
    void deleteLog() throws SQLException {
        logDAO.createLog("5", "b2", "b4");
        logDAO.createLog("5", "b7", "b5");
        logDAO.createLog("5", "c2", "c4");
        logDAO.deleteLogByRoomId("5");
    }
}
