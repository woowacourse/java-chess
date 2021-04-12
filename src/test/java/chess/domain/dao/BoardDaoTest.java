package chess.domain.dao;

import chess.dao.BoardDao;
import chess.domain.Side;
import chess.domain.board.Board;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BoardDaoTest {

    public static final String GameNumber = "1";

    private BoardDao boardDAO;
    private Board board;

    @BeforeEach
    void setUp() {
        boardDAO = new BoardDao();
        board = Board.getGamingBoard();
    }

    @Test
    void connection() {
        Connection con = boardDAO.getConnection();
        assertNotNull(con);
    }

    @Test
    void addBoard() throws SQLException {
        boardDAO.initBoardTable();
        boardDAO.addBoard(board, Side.WHITE.name());
    }

    @Test
    void findTurn() throws SQLException {
        assertThat(boardDAO.findTurn(GameNumber)).isEqualTo(Side.WHITE);
    }

    @Test
    void updateBoard() throws SQLException {
        board.move(Position.from("f2"), Position.from("f4"), Side.WHITE);
        boardDAO.updateBoard(board, Side.WHITE.name());
    }
}
