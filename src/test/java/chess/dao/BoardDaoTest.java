package chess.dao;

import chess.service.BoardDto;
import chess.service.PieceDto;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardDaoTest {
    private BoardDao boardDao;

    @BeforeEach
    void setUp() {
        boardDao = new BoardDao();
    }

    @Test
    void saveTest() {
        PieceDto firstPiece = new PieceDto("pawn", "black");
        Map<String, PieceDto> board = Map.of("a1", firstPiece);
        BoardDto boardDto = new BoardDto(board);
        boardDao.initBoard(boardDto, 11);
    }

    @Test
    void move() {
        boardDao.move("a2", "a4", 11);
    }
}