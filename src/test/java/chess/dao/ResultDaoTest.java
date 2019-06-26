package chess.dao;

import chess.dto.ResultDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultDaoTest {

    ResultDao resultDao;

    @BeforeEach
    void setUp() {
        resultDao = ResultDao.getInstance();
    }

    @Test
    void result_데이터_추가_조회_테스트() throws SQLException {
        String name = "p";
        String team = "BLACK";
        int count = 1;
        ResultDto resultDto = new ResultDto(name, team, count);

        resultDao.add(resultDto);
        ResultDto findResult = resultDao.find().get(0);

        assertThat(findResult.getName()).isEqualTo(name);
        assertThat(findResult.getTeam()).isEqualTo(team);
        assertThat(findResult.getCount()).isEqualTo(count);

        resultDao.delete();
    }
}
