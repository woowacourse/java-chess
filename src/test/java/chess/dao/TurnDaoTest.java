package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.state.Turn;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class TurnDaoTest {

    TurnDaoImpl turnDao = new TurnDaoImpl();

    @AfterEach
    void tearDown() {
        turnDao.updateTurn(turnDao.findCurrentTurn(), Turn.WHITE_TURN);
    }

    @Test
    void findCurrentTurn() {
        Turn currentTurn = turnDao.findCurrentTurn();
        assertThat(currentTurn).isEqualTo(Turn.WHITE_TURN);
    }

    @Test
    void updateTurn() {
        turnDao.updateTurn(turnDao.findCurrentTurn(), Turn.BLACK_TURN);
        Turn currentTurn = turnDao.findCurrentTurn();

        assertThat(currentTurn).isEqualTo(Turn.BLACK_TURN);
    }
}
