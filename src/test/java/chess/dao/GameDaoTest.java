package chess.dao;

import chess.domain.DBConnector;
import chess.dto.GameDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class GameDaoTest {
    @Test
    void GameCURDTest() {
        DBConnector.setDATABASE("chess_test_db");
        GameDao gameDao = GameDaoImpl.getInstance();

        int gameId = gameDao.addGame();

        GameDto gameDto = new GameDto(gameId, false, 2);
        assertThat(gameDao.findById(gameId)).isEqualTo(gameDto);

        GameDto newGameDto = new GameDto(gameId, true, 1);
        assertThat(gameDao.updateGame(newGameDto)).isEqualTo(1);

        assertThat(gameDao.deleteGameByid(gameId)).isEqualTo(1);

        DBConnector.setDATABASE("chess_db");
    }
}
