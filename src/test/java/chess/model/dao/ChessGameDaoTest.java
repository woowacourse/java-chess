package chess.model.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.domain.board.Turn;
import chess.model.domain.piece.Color;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    private static final long TEST_CHESS_GAME_ID = 1;
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

        assertThat(loadedTurn.getTurn())
                .isEqualTo(turn.getTurn());
    }

    @Test
    @DisplayName("턴을 조회하는 기능 테스트")
    void loadTurn() {
        final Turn loadedTurn = chessGameDao.loadTurn(TEST_CHESS_GAME_ID);

        assertThat(loadedTurn.getTurn())
                .isEqualTo(TEST_TURN.getTurn());
    }

    @Test
    @DisplayName("현재 존재하는 모든 ID를 가져오는 기능 테스트")
    void findAllId() {
        final List<Long> allId = chessGameDao.findAllId();

        assertThat(allId)
                .containsExactly(TEST_CHESS_GAME_ID);
    }

    @Test
    @DisplayName("새로운 게임을 생성하는 기능 테스트")
    void generateNewGame() {
        final long newGameId = chessGameDao.generateNewGame();

        assertThat(chessGameDao.findAllId())
                .containsExactly(TEST_CHESS_GAME_ID, newGameId);
    }

    @Test
    @DisplayName("게임을 삭제하는 기능 테스트")
    void deleteGameTest() {
        chessGameDao.deleteGame(TEST_CHESS_GAME_ID);

        assertThat(chessGameDao.findAllId())
                .containsExactly();
    }
}
