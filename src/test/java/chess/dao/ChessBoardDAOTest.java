package chess.dao;

import chess.domain.ChessBoard;
import chess.domain.piece.Color;
import chess.dto.ChessBoardDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChessBoardDAOTest {
    private ChessBoardDAO chessBoardDAO;
    ChessBoard chessBoard = new ChessBoard("player1", Color.WHITE);

    @BeforeEach
    public void setup() {
        chessBoardDAO = new ChessBoardDAO();
    }

    @Test
    public void connection() {
        Connection con = chessBoardDAO.getConnection();
        assertNotNull(con);
    }

    @Test
    public void addChessBoard() throws Exception {
        ChessBoardDTO chessBoardDTO = new ChessBoardDTO(chessBoard);
        chessBoardDAO.addChessBoard(chessBoardDTO);
    }

    @Test
    public void findByPlayerId() throws Exception {
        String turn = chessBoardDAO.findByPlayerId("player1");
        assertEquals(chessBoard.getTurn().getTurn().getName(), turn);
    }

    @Test
    public void updateChessBoard() throws Exception {
        chessBoardDAO.updateChessBoard("player1", "Black");
        Color turn = Color.of(chessBoardDAO.findByPlayerId("player1"));
        assertEquals(turn.getName(), "Black");
    }

    @Test
    public void deleteChessBoard() throws Exception {
        chessBoardDAO.deleteChessBoard("player1");
        assertNull(chessBoardDAO.findByPlayerId("player1"));
    }

}
