package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.dto.ChessDto;
import chess.util.JdbcTemplate;
import chess.util.JdbcTestFixture;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    private BoardDao boardDao;

    @BeforeEach
    void setUp() {
        Connection connection = JdbcTemplate.getConnection(JdbcTestFixture.DEV_URL);
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boardDao = new BoardDaoImpl(connection);
    }

    @Test
    void updateAndGet() {
        Map<String, String> map = new HashMap<>();

        map.put("g2", "white_pawn");
        map.put("h2", "white_pawn");
        boardDao.updateBatchPositions(map);

        Map<String, String> expected = ChessDto.of(new Board(BoardFactory.initialize())).getBoard();

        assertThat(boardDao.getBoard()).isEqualTo(expected);
    }


    @AfterEach
    void teardown() {
        Connection connection = JdbcTemplate.getConnection(JdbcTestFixture.DEV_URL);
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
