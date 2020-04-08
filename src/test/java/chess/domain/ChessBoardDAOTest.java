package chess.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChessBoardDAOTest {
    private ChessBoardDAO chessBoardDAO;

    @BeforeEach
    public void setup() {
        chessBoardDAO = new ChessBoardDAO();
    }

    @Test
    public void connection() {
        Connection con = chessBoardDAO.getConnection();
        assertNotNull(con);
    }

}
