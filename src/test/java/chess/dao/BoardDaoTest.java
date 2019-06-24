package chess.dao;

import chess.dto.BoardDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardDaoTest {

    BoardDao boardDao;

    @BeforeEach
    void setUp() {
        boardDao = new BoardDao();
    }

    @Test
    void board_데이터_추가_조회_테스트() throws SQLException {
        Map<String, String> board = new HashMap<>();
        board.put("a1", "r");
        BoardDto boardDto = new BoardDto();

        boardDto.setBoard(board);
        boardDao.add(boardDto);
        BoardDto findResult = boardDao.findAll();
        assertThat(findResult.getBoard().get("a1")).isEqualTo("r");

        boardDao.deleteAll();
    }
}
