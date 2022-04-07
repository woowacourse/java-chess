package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dto.TurnDto;
import org.junit.jupiter.api.Test;

class GameDaoTest {

    @Test
    void getTurn() {
        GameDaoImpl gameDao = new GameDaoImpl();
        TurnDto turnDto = gameDao.getTurn();
        assertThat(turnDto.getTurn()).isEqualTo("WHITE");
    }

    @Test
    void changeTurn() {
        GameDaoImpl gameDao = new GameDaoImpl();
        TurnDto beforeTurnDto = gameDao.getTurn();

        gameDao.changeTurn();

        TurnDto afterTurnDto = gameDao.getTurn();

        assertThat(beforeTurnDto.getTurn()).isNotEqualTo(afterTurnDto.getTurn());
    }
}
