package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.position.Position;
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
        connection = new BoardDao().getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void connection() {
        BoardDao boardDao = new BoardDao();
        Connection connection = boardDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void loadBoard() {
        BoardDao boardDao = new BoardDao();
        BoardDto boardDto = boardDao.loadBoard();
        assertThat(boardDto.getBoard()).isNotNull();
    }

    @Test
    void movePiece() {
        BoardDao boardDao = new BoardDao();
        boardDao.movePiece(new CommandDto("a2 a3"));

        BoardDto boardDto = boardDao.loadBoard();
        Map<String, List<String>> board = boardDto.getBoard();
        List<String> pieceAndColor = board.get("a3");
        assertThat(pieceAndColor.get(0)).isNotNull();
    }
}
