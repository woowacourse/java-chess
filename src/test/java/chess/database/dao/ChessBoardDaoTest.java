package chess.database.dao;

import chess.domain.ChessBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChessBoardDaoTest {
    private ChessBoardDao chessBoardDao;

    @BeforeEach
    void setUp() {
        chessBoardDao = ChessBoardDao.getInstance();
    }

    @Test
    public void connection() {
        Connection con = chessBoardDao.getConnection();
        assertNotNull(con);
        chessBoardDao.closeConnection(con);
    }

    @Test
    public void saveRetrieveTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        chessBoardDao.save(chessBoard);
        assertEquals(chessBoard, chessBoardDao.retrieve());
    }
}
