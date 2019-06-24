package chess.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class ChessGameDaoTest {

    private ChessGameDao chessGameDao;

    @BeforeEach
    void setup() {
        chessGameDao = new ChessGameDao(DBManager.createDataSource());
    }

    @Test
    void test1_게임_테이블_추가() {
        assertDoesNotThrow(() -> chessGameDao.addGame());
    }

    @Test
    void test2_게임_테이블_턴_검색() throws SQLException {
        assertEquals(chessGameDao.findTurn(), "white");
    }

    @Test
    void test3_게임_테이블_삭제() {
        assertDoesNotThrow(() -> chessGameDao.deleteGame());
    }

    @Test
    void test4_게임_테이블_null_확인() throws SQLException {
        assertEquals(chessGameDao.findTurn(), null);
    }

}
