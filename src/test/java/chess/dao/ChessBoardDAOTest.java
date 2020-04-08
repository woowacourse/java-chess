package chess.dao;

import chess.domain.ChessBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChessBoardDAOTest {
    private ChessBoardDAO chessBoardDAO;

    @BeforeEach
    void setUp() {
        chessBoardDAO = ChessBoardDAO.getInstance();
    }

    @Test
    public void connection() {
        Connection con = chessBoardDAO.getConnection();
        assertNotNull(con);
        chessBoardDAO.closeConnection(con);
    }

    @Test
    public void saveRetrieveTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        chessBoardDAO.save(chessBoard);
        assertEquals(chessBoard, chessBoardDAO.retrieve());
    }
}
