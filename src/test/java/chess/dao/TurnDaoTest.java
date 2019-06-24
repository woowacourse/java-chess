package chess.dao;

import chess.dto.TurnDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class TurnDaoTest {

    TurnDao turnDao;

    @BeforeEach
    void setUp() {
        turnDao = TurnDao.getInstance();
    }

    @Test
    void turn데이터_추가_조회_테스트() throws SQLException {
        TurnDto turnDto = new TurnDto();
        turnDto.setTeam("WHITE");

        turnDao.add(turnDto);
        TurnDto findResult = turnDao.find();

        assertThat(findResult.getTeam()).isEqualTo("WHITE");

        turnDao.delete();
    }
}
