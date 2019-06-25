package chess.dao;

import chess.dto.BoardDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardDaoTest {

    BoardDao boardDao;

    @BeforeEach
    void setUp() {
        boardDao = BoardDao.getInstance();
    }

    @Test
    void board_데이터_추가_조회_테스트() throws SQLException {
        BoardDto boardDto = new BoardDto();
        boardDto.setPosition("a1");
        boardDto.setPieceName("r");
        boardDto.setTeam("BLACK");

        boardDao.add(boardDto);

        List<BoardDto> findResult = boardDao.findAll();
        assertThat(findResult.get(0).getPosition()).isEqualTo("a1");
        assertThat(findResult.get(0).getPieceName()).isEqualTo("r");
        assertThat(findResult.get(0).getPieceName()).isEqualTo("BLACK");

        boardDao.deleteAll();
    }
}
