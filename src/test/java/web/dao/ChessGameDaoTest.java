package web.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import web.dao.ChessGameDao;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChessGameDaoTest {
    private ChessGameDao chessGameDao;

    @BeforeEach
    public void setup() {
        chessGameDao = new ChessGameDao();
    }

    @Test
    @DisplayName("db 연결 확인")
    public void connection() throws SQLException {
        try (Connection con = chessGameDao.getConnection()) {
            assertNotNull(con);
        }
    }
}
