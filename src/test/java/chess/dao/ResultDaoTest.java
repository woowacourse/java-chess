package chess.dao;

import chess.dto.ResultDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultDaoTest {

    ResultDao resultDao;

    @BeforeEach
    void setUp() {
        resultDao = ResultDao.getInstance();
    }

    @Test
    void result_데이터_추가_조회_테스트() throws SQLException {
        ResultDto resultDto = new ResultDto();
        resultDto.setBlackScore(10);
        resultDto.setWhiteScore(20.5);

        resultDao.add(resultDto);
        ResultDto findResult = resultDao.find();

        assertThat(findResult.getBlackScore()).isEqualTo(10);
        assertThat(findResult.getWhiteScore()).isEqualTo(20.5);
    }
}
