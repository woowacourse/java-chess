package domain.dao;

import static org.assertj.core.api.Assertions.assertThat;

import common.JdbcConnection;
import domain.Location;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.type.Color;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {

    private final JdbcConnection jdbcConnection = new JdbcConnection();
    private final TestJdbcContext testJdbcContext = new TestJdbcContext(jdbcConnection);
    private final PieceDao pieceDao = new PieceDaoImpl(testJdbcContext);
    private final BoardDao boardDao = new BoardDaoImpl(testJdbcContext);
    private final Map<Location, Piece> board = Map.of(
        Location.of(1, 2), Pawn.makeBlack(),
        Location.of(2, 2), King.makeBlack(),
        Location.of(3, 2), Queen.makeBlack(),
        Location.of(4, 2), Knight.makeBlack()
    );

    @Test
    @DisplayName("insert 테스트")
    public void testInsert() {
        testJdbcContext.makeTransactionUnit(() -> {
            //given
            final String boardId = "test";
            boardDao.insert(boardId, Color.WHITE);

            //when
            pieceDao.insert(board, boardId);

            //then
            final Map<Location, Piece> result = pieceDao.findAllByBoardId(boardId);
            for (final Location location : result.keySet()) {
                assertThat(result.get(location)).isEqualTo(board.get(location));
            }
            return null;
        });
    }

    @Test
    @DisplayName("update 테스트")
    public void testUpdate() {
        testJdbcContext.makeTransactionUnit(() -> {
            //given
            final String boardId = "test";
            boardDao.insert(boardId, Color.WHITE);
            pieceDao.insert(board, boardId);
            final Map<Location, Piece> modifiedBoard = Map.of(
                Location.of(1, 2), Pawn.makeWhite(),
                Location.of(2, 2), King.makeWhite(),
                Location.of(3, 2), Queen.makeWhite(),
                Location.of(4, 2), Knight.makeWhite()
            );

            //when
            final Integer count = pieceDao.update(modifiedBoard, boardId);

            //then
            assertThat(count).isEqualTo(4);
            final Map<Location, Piece> result = pieceDao.findAllByBoardId(boardId);
            for (final Location location : result.keySet()) {
                assertThat(result.get(location)).isEqualTo(modifiedBoard.get(location));
            }
            return null;
        });
    }

    @Test
    @DisplayName("boardId와 일치하는 모든 조각 테스트")
    public void testFindAllByBoardId() {
        testJdbcContext.makeTransactionUnit(() -> {
            //given
            final String boardId = "test";
            boardDao.insert(boardId, Color.WHITE);
            pieceDao.insert(board, boardId);

            //when
            final Map<Location, Piece> result = pieceDao.findAllByBoardId(boardId);

            //then
            for (final Location location : result.keySet()) {
                assertThat(result.get(location)).isEqualTo(board.get(location));
            }
            return null;
        });
    }
}
