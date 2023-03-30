package chess.model.dao;

import chess.model.domain.board.Turn;
import chess.model.domain.piece.Color;
import java.sql.SQLException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    private static final int TEST_CHESS_GAME_ID = 1;
    private static final Turn TEST_TURN = new Turn(Color.WHITE);

    private TestJdbcTemplate testJdbcTemplate;
    private DataBaseChessGameDao chessGameDao;

    @BeforeEach
    void setUp() {
        testJdbcTemplate = new TestJdbcTemplate(TestConnectionGenerator.getConnection());
        chessGameDao = new DataBaseChessGameDao(testJdbcTemplate);
    }

    @AfterEach
    void tearDown() throws SQLException {
        testJdbcTemplate.rollBack();
    }

    @Test
    @DisplayName("턴을 업데이트하는 기능 테스트")
    void updateTurn() {
        final Turn turn = new Turn(Color.BLACK);

        chessGameDao.updateTurn(turn, TEST_CHESS_GAME_ID);
        final Turn loadedTurn = chessGameDao.loadTurn(TEST_CHESS_GAME_ID);

        Assertions.assertThat(loadedTurn.getTurn())
                .isEqualTo(turn.getTurn());
    }

    @Test
    @DisplayName("턴을 조회하는 기능 테스트")
    void loadTurn() {
        final Turn loadedTurn = chessGameDao.loadTurn(TEST_CHESS_GAME_ID);

        Assertions.assertThat(loadedTurn.getTurn())
                .isEqualTo(TEST_TURN.getTurn());
    }
}
