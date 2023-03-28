package domain.dao;

import static org.assertj.core.api.Assertions.assertThat;

import common.JdbcConnection;
import domain.type.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    private final JdbcConnection jdbcConnection = new JdbcConnection();
    private final TestJdbcContext testJdbcContext = new TestJdbcContext(jdbcConnection);
    private final BoardDao boardDao = new BoardDaoImpl(testJdbcContext);

    @Test
    @DisplayName("insert 테스트")
    public void testInsert() {
        testJdbcContext.makeTransactionUnit(() -> {
            //given
            final String boardId = "test1";
            final Color color = Color.WHITE;

            //when
            boardDao.insert(boardId, color);

            //then
            final Integer count = boardDao.count(boardId);
            assertThat(count).isEqualTo(1);
            return null;
        });
    }

    @Test
    @DisplayName("update 테스트")
    public void testUpdate() {
        testJdbcContext.makeTransactionUnit(() -> {
            //given
            final String boardId = "test1";
            final Color color = Color.WHITE;
            boardDao.insert(boardId, color);

            //when
            final Integer count = boardDao.update(boardId, color);

            //then
            assertThat(count).isEqualTo(1);
            return null;
        });
    }

    @Test
    @DisplayName("가장 최근 색 찾기 테스트")
    public void testFindLastColor() {
        testJdbcContext.makeTransactionUnit(() -> {
            //given
            final String boardId = "test1";
            final Color color = Color.WHITE;
            boardDao.insert(boardId, color);

            //when
            final Color result = boardDao.findLastColor(boardId);

            //then
            assertThat(result).isEqualTo(color);
            return null;
        });
    }

    @Test
    @DisplayName("id와 일치하는 개수 찾기 테스트")
    public void testCount() {
        testJdbcContext.makeTransactionUnit(() -> {
            //given
            final String boardId = "test1";
            final Color color = Color.WHITE;
            boardDao.insert(boardId, color);

            //when
            final int result = boardDao.count(boardId);

            //then
            assertThat(result).isEqualTo(1);
            return null;
        });
    }
}
