package chess.domain.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class ChessBoardDAOTest {
    private ChessBoardDAO chessBoardDAO;

    @BeforeEach
    public void setup() {
        chessBoardDAO = new ChessBoardDAO();
    }

    @Test
    @DisplayName("DB 연결 테스트")
    public void connection() {
        Connection con = chessBoardDAO.getConnection();
        assertNotNull(con);
    }

    @Test
    void closeConnection() {
    }

    @Test
    void addPosition() {
    }

    @Test
    void removePositions() {
    }
}