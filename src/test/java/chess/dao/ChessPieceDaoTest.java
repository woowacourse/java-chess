package chess.dao;

import chess.service.dto.PieceDto;
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
        PieceDto pieceDto = new PieceDto();
        pieceDto.setPoint("a1");
        pieceDto.setColor("WHITE");
        pieceDto.setType("KING");
        assertDoesNotThrow(() -> chessPieceDao.addPiece(pieceDto));
    }

    @Test
    void test2_체스_말_위치_업데이트() {
        PieceDto sourcePieceDto = new PieceDto();
        PieceDto targetPieceDto = new PieceDto();
        targetPieceDto.setPoint("a1");
        sourcePieceDto.setColor("BLACK");
        sourcePieceDto.setType("KNIGHT");
        assertDoesNotThrow(() -> chessPieceDao.updatePiece(sourcePieceDto, targetPieceDto));
    }

    @Test
    void test3_체스_말_삭제() {
        assertDoesNotThrow(() -> chessPieceDao.deletePiece("a1"));
    }
}
