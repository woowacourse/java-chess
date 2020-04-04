package chess;

import chess.dao.PieceDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PieceDAOTest {
    private PieceDAO pieceDAO;

    @BeforeEach
    public void setUp() {
        pieceDAO = PieceDAO.getInstance();
    }

    @Test
    public void connection() {
        Connection con = pieceDAO.getConnection();
        assertNotNull(con);
    }
}