package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dto.BoardDto;
import chess.dto.CommandDto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    private Connection connection;

    @BeforeEach
    void beforeEach() {
        connection = JdbcTemplateUtil.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void loadBoard() {
        BoardDaoImpl boardDao = new BoardDaoImpl();
        BoardDto boardDto = boardDao.loadBoard();
        assertThat(boardDto.getBoard()).isNotNull();
    }

    @Test
    void movePiece() {
        BoardDaoImpl boardDao = new BoardDaoImpl();
        boardDao.movePiece(new CommandDto("a2 a3"));

        BoardDto boardDto = boardDao.loadBoard();
        Map<String, List<String>> board = boardDto.getBoard();
        List<String> pieceAndColor = board.get("a3");
        assertThat(pieceAndColor.get(0)).isNotNull();
    }
}
