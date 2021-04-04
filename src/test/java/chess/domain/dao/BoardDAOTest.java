package chess.domain.dao;

import chess.dao.BoardDAO;
import chess.domain.Board;
import chess.domain.Side;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BoardDAOTest {
    private BoardDAO boardDAO;
    private Board board;

    @BeforeEach
    void setUp() {
        boardDAO = new BoardDAO();
        board = Board.getGamingBoard();
    }

    @Test
    void connection() {
        Connection con = boardDAO.getConnection();
        assertNotNull(con);
    }

    @Test
    void addBoard() throws SQLException {
        boardDAO.addBoard(board, "White");
    }

    @Test
    void updateBoard() throws SQLException {
        board.move(Position.from("f2"), Position.from("f4"), Side.WHITE);
        boardDAO.updateBoard(board, "White");
    }
}
