package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.web.dto.TurnDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnDaoTest {

    private TurnDao turnDao;

    @BeforeEach
    void setUp() {
        turnDao = new MemoryTurnDao();
    }

    @DisplayName("현재 turn 정보를 저장한다.")
    @Test
    void 턴_정보_저장한다() {
        TurnDto turnDto = new TurnDto("ready");

        turnDao.save(turnDto);

        assertThat(turnDao.findLastTurn()).isEqualTo(turnDto);
    }
}
