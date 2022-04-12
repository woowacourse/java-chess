package chess.dao;

import chess.domain.Team;
import chess.dto.GameData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameDaoTest {

    @Test
    void saveGameTest() {
        GameDao gameDao = new GameDao();
        gameDao.saveGame(GameData.of(1111, Team.of(Team.WHITE)));
    }

    @Test
    void getTurnTest() {
        GameDao gameDao = new GameDao();
        gameDao.saveGame(GameData.of(1111, Team.of(Team.WHITE)));
        String turn = gameDao.getTurn(1111);
        assertThat(turn).isEqualTo(Team.of(Team.WHITE));
    }

    @Test
    void deleteGameData() {
        GameDao gameDao = new GameDao();
        gameDao.saveGame(GameData.of(1112, Team.of(Team.WHITE)));
        gameDao.deleteGameData(1112);
    }

    @Test
    void updateGameDataTest() {
        GameDao gameDao = new GameDao();
        gameDao.saveGame(GameData.of(1111, Team.of(Team.WHITE)));
        gameDao.updateGameData(GameData.of(1111, Team.of(Team.BLACK)));
        String turn = gameDao.getTurn(1111);
        assertThat(turn).isEqualTo(Team.of(Team.BLACK));
    }

    @AfterEach
    void afterEach() {
        GameDao gameDao = new GameDao();
        gameDao.deleteGameData(1111);
    }
}
