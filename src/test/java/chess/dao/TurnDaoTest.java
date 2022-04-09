package chess.dao;

import chess.dao.fake.FakeTurnDao;
import chess.domain.piece.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TurnDaoTest {

    private TurnDao turnDao;

    @BeforeEach
    void init() {
        turnDao = new FakeTurnDao();
        turnDao.reset();
    }

    @DisplayName("초기 값을 확인한다.")
    @Test
    void getTurn() {
        // given
        Team initTurn = Team.WHITE;

        // then
        Assertions.assertThat(turnDao.getTurn()).isEqualTo(initTurn.toString());
    }

    @DisplayName("차례를 변경 후 변경 값을 확인한다.")
    @Test
    void nextTurn() {
        // given
        Team initTurn = Team.WHITE;
        Team nextTurn = Team.BLACK;
        //when
        turnDao.update(initTurn.toString(), nextTurn.toString());
        // then
        Assertions.assertThat(turnDao.getTurn()).isEqualTo(nextTurn.toString());
    }

    @DisplayName("리셋을 확인한다.")
    @Test
    void reset() {
        // given
        Team initTurn = Team.WHITE;
        Team nextTurn = Team.BLACK;
        //when
        turnDao.update(initTurn.toString(), nextTurn.toString());
        turnDao.reset();
        // then
        Assertions.assertThat(turnDao.getTurn()).isEqualTo(initTurn.toString());
    }
}
