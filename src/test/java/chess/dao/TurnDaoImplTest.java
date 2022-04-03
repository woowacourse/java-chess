package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.turn.Turn;
import chess.testutil.H2Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnDaoImplTest {

    private TurnDao turnDao;

    @BeforeEach
    void setUp() {
        H2Connection.setUpTable();
        turnDao = new TurnDaoImpl(H2Connection.getConnection());
    }

    @Test
    @DisplayName("현재 턴 반환")
    void findCurrentTurn() {
        Turn turn = turnDao.findCurrentTurn()
                .orElseThrow(() -> new RuntimeException("현재 턴이 존재하지 않는다."));
        assertThat(turn).isEqualTo(Turn.END);
    }

    @Test
    @DisplayName("턴 업데이트")
    void updateTurn() {
        // given
        Turn currentTurn = turnDao.findCurrentTurn()
                .orElseThrow(() -> new RuntimeException("현재 턴이 존재하지 않는다."));
        Turn nextTurn = Turn.WHITE_TURN;

        // when
        turnDao.updateTurn(currentTurn, nextTurn);
        Turn changedTurn = turnDao.findCurrentTurn()
                .orElseThrow(() -> new RuntimeException("변경된 현재 턴이 존재하지 않는다."));

        // then
        assertThat(changedTurn).isEqualTo(Turn.WHITE_TURN);
    }
}
