package chess.domain.pieceTest;

import chess.domain.piece.PieceDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PieceDAOTest {
    private PieceDAO pieceDAO;

    @BeforeEach
    void setUp() {
        pieceDAO = new PieceDAO();
    }

    @Test
    public void connection() {
        Connection con = pieceDAO.getConnection();
        assertNotNull(con);
        pieceDAO.closeConnection(con);
    }

}
