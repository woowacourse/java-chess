package chess.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class ChessPieceDaoTest {

    private ChessPieceDao chessPieceDao;

    @BeforeEach
    void setup() {
        chessPieceDao = new ChessPieceDao(DBManager.createDataSource());
    }

    @Test
    void test1_체스_말_추가() {
        assertDoesNotThrow(() -> chessPieceDao.addPiece("a1", "WHITE", "KING"));
    }

    @Test
    void test2_체스_말_위치_업데이트() {
        assertDoesNotThrow(() -> chessPieceDao.updatePiece("a1", "BLACK", "KNIGHT"));
    }

    @Test
    void test3_체스_말_삭제() {
        assertDoesNotThrow(() -> chessPieceDao.deletePieceByPoint("a1"));
    }
}
