package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.state.StateType;
import chess.web.dto.TurnDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnDaoTest {

    private TurnDao turnDao;

    @BeforeEach
    void setUp() {
        turnDao = new TurnDaoImpl();
    }

    @DisplayName("현재 turn 정보를 저장한다.")
    @Test
    void 턴_정보_저장한다() {
        TurnDto turnDto = new TurnDto(StateType.WHITE_TURN);

        turnDao.save(turnDto);

        assertThat(turnDao.findLastTurn().get()).isEqualTo(turnDto);
    }

    @DisplayName("턴의 마지막 정보를 조회한다.")
    @Test
    void 턴_조회한다() {
        TurnDto turnDto = new TurnDto(StateType.READY);
        turnDao.save(turnDto);

        TurnDto findTurn = turnDao.findLastTurn().get();

        assertThat(findTurn.getTurn()).isEqualTo(StateType.READY);
    }
}
