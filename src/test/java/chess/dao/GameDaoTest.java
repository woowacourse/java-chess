package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dto.TurnDto;
import java.sql.Connection;
import org.junit.jupiter.api.Test;

class GameDaoTest {

    @Test
    void connection() {
        GameDao gameDao = new GameDao();
        Connection connection = gameDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void getTurn() {
        GameDao gameDao = new GameDao();
        TurnDto turnDto = gameDao.getTurn();
        assertThat(turnDto.getTurn()).isEqualTo("WHITE");
    }

    @Test
    void changeTurn() {
        GameDao gameDao = new GameDao();
        TurnDto beforeTurnDto = gameDao.getTurn();

        gameDao.changeTurn();

        TurnDto afterTurnDto = gameDao.getTurn();

        assertThat(beforeTurnDto.getTurn()).isNotEqualTo(afterTurnDto.getTurn());
    }
}
