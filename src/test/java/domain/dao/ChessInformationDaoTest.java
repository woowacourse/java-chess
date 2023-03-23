package domain.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import common.TransactionStrategy;
import domain.Location;
import domain.piece.Pawn;
import domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessInformationDaoTest {

    private final ChessInformationDao chessInformationDao = new ChessInformationDaoImpl();
    private final TestTransactionContext testTransactionContext = new TestTransactionContext();

    @Test
    @DisplayName("insert 테스트")
    public void testInsert() {
        //given
        final String boardId = "test1";
        final Map<Location, Piece> board = new HashMap<>();
        final int row = 5;
        for (int i = 1; i < 5; i++) {
            final Location location = Location.of(i, row);
            board.put(location, Pawn.makeWhite());
        }

        //when
        //then
        assertDoesNotThrow(() ->
            testTransactionContext.workWithTransaction((TransactionStrategy<Void>) connection -> {
                    chessInformationDao.insert(board, boardId, connection);
                    final Integer count = chessInformationDao.count(boardId,
                        connection);
                    assertThat(count).isEqualTo(1);
                    return null;
                }
            ));
    }

    @Test
    @DisplayName("count 테스트")
    public void testCount() {
        //given
        final String boardId = "test1";
        final Map<Location, Piece> board = new HashMap<>();
        final int row = 5;
        for (int i = 1; i < 5; i++) {
            final Location location = Location.of(i, row);
            board.put(location, Pawn.makeWhite());
        }

        //when
        //then
        assertDoesNotThrow(() ->
            testTransactionContext.workWithTransaction((TransactionStrategy<Void>) connection -> {
                    chessInformationDao.insert(board, boardId, connection);
                    final Integer count = chessInformationDao.count(boardId,
                        connection);
                    assertThat(count).isEqualTo(1);
                    return null;
                }
            ));
    }

    @Test
    @DisplayName("find 테스트")
    public void testFind() {
        //given
        final String boardId = "test1";
        final Map<Location, Piece> board = new HashMap<>();
        final int row = 5;
        for (int i = 1; i < 5; i++) {
            final Location location = Location.of(i, row);
            board.put(location, Pawn.makeWhite());
        }

        //when
        //then
        assertDoesNotThrow(() ->
            testTransactionContext.workWithTransaction((TransactionStrategy<Void>) connection -> {
                    chessInformationDao.insert(board, boardId, connection);
                    final Map<Location, Piece> result = chessInformationDao.find(boardId, connection);
                    assertThat(result.size()).isEqualTo(4);
                    return null;
                }
            ));
    }
}
