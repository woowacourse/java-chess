package chess.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class ChessDAOTest {
    private ChessDAO chessDAO;
//    private static final User USER = new User("testUserId", "testUser");

    @BeforeEach
    public void setup() {
        chessDAO = new ChessDAO();
    }

    @Test
    public void connection() {
        Connection con = chessDAO.getConnection();
        assertNotNull(con);
    }



}