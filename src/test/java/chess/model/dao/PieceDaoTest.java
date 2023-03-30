package chess.model.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.model.domain.board.Board;
import chess.model.domain.board.BoardFactory;
import chess.model.domain.board.Turn;
import chess.model.domain.piece.Color;
import chess.model.domain.piece.Piece;
import chess.model.domain.position.Position;
import java.sql.SQLException;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {

    private final long TEST_GAME_ID = 1L;
    private final Board TEST_BOARD = new BoardFactory().createInitialBoard();
    private final Turn TEST_TURN = new Turn(Color.WHITE);

    private TestJdbcTemplate testJdbcTemplate;
    private DataBasePieceDao pieceDao;

    @BeforeEach
    void setUp() {
        testJdbcTemplate = new TestJdbcTemplate(TestConnectionGenerator.getConnection());
        pieceDao = new DataBasePieceDao(testJdbcTemplate);
    }

    @AfterEach
    void tearDown() throws SQLException {
        testJdbcTemplate.rollBack();
    }

    @Test
    @DisplayName("보드를 불러오는 기능 테스트")
    void test_loadBoard() {
        final Board loadedBoard = pieceDao.loadBoard(TEST_GAME_ID, TEST_TURN);

        assertThat(loadedBoard.getBoard())
                .containsAllEntriesOf(TEST_BOARD.getBoard());
    }

    @Test
    @DisplayName("보드를 저장하는 기능 테스트")
    void test_saveBoard() {
        final long gameId = 2L;
        final Board board = new BoardFactory().createInitialBoard();
        pieceDao.saveBoard(board, gameId);
        final Board loadedBoard = pieceDao.loadBoard(gameId, board.getTurn());

        assertThat(loadedBoard.getBoard())
                .containsAllEntriesOf(board.getBoard());
    }

    @Test
    @DisplayName("기물의 위치를 업데이트하는 기능 테스트")
    void test_updatePiecePosition() {
        final Position from = new Position(2, 2);
        final Position to = new Position(2, 4);

        pieceDao.updatePiecePosition(from, to, TEST_GAME_ID);
        final Map<Position, Piece> board = pieceDao.loadBoard(TEST_GAME_ID, TEST_TURN)
                .getBoard();

        assertAll(
                () -> assertFalse(board.containsKey(from)),
                () -> assertTrue(board.containsKey(to))
        );
    }

    @Test
    @DisplayName("관련 테이블을 삭제하는 기능 테스트")
    void test_deleteBoard() {
        pieceDao.deleteBoard(TEST_GAME_ID);
        final Board board = pieceDao.loadBoard(TEST_GAME_ID, TEST_TURN);

        assertThat(board.getBoard())
                .containsExactly();
    }
}
