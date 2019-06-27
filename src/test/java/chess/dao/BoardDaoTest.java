package chess.dao;

import chess.dto.BoardDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BoardDaoTest {
    private BoardDao boardDao;
    private List<BoardDto> boardDtos;

    @BeforeEach
    void setUp() {
        boardDao = new BoardDao();
        boardDtos = Arrays.asList(new BoardDto("king", "WHITE", "5,1", 1));
    }

    @Test
    void 보드_초기화() {
        boardDao.initialize(boardDtos);
    }

    @Test
    void 최신_round_조회() {
        assertThat(boardDao.recentRound()).isEqualTo(1);
    }

    @Test
    void round_없을때_조회() {
        assertThat(boardDao.recentRound()).isEqualTo(0);
    }

    @Test
    void round에_따른_체스말_조회() {
        assertThat(boardDao.findChessesByRound(1)).isEqualTo(boardDtos);
    }

    @Test
    void 체스말_삭제() {
        boardDao.remove(1,"5,1");
    }

    @Test
    void 체스말_위치_변경() {
        boardDao.initialize(boardDtos);
        boardDao.update(1,"5,1","5,2");
    }

}