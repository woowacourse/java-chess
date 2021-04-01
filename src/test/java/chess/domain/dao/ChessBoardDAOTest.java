package chess.domain.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    void addPosition() throws SQLException {
        ChessBoardDTOForDAO chessBoardDTOForDAO = new ChessBoardDTOForDAO("a8", "WHITE", "ROOK", "alive");
        System.out.println(chessBoardDTOForDAO.getPosition());
        chessBoardDAO.addPosition(chessBoardDTOForDAO);
    }

    @Test
    void removePositions() throws SQLException {
        chessBoardDAO.removePositions();
    }
}