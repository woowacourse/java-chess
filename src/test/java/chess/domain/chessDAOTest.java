package chess.domain;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class chessDAOTest {
    private static ChessDAO chessDAO;

    @Test
    public void connection() {
        chessDAO = new ChessDAO();
        Connection con = chessDAO.getConnection();
        assertNotNull(con);
    }

}