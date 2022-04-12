package chess.dao;

import chess.domain.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DbGameDaoTest {

    @Test
    void saveGameTest() {
        DbGameDao dbGameDao = new DbGameDao();
        dbGameDao.saveGame(1111, Team.of(Team.WHITE));
    }

    @Test
    void getTurnTest() {
        DbGameDao dbGameDao = new DbGameDao();
        dbGameDao.saveGame(1111, Team.of(Team.WHITE));
        String turn = dbGameDao.getTurn(1111);
        assertThat(turn).isEqualTo(Team.of(Team.WHITE));
    }

    @Test
    void deleteGameData() {
        DbGameDao dbGameDao = new DbGameDao();
        dbGameDao.saveGame(1112, Team.of(Team.WHITE));
        dbGameDao.deleteGameData(1112);
    }

    @Test
    void updateGameDataTest() {
        DbGameDao dbGameDao = new DbGameDao();
        dbGameDao.saveGame(1111, Team.of(Team.WHITE));
        dbGameDao.updateGameData(1111, Team.of(Team.BLACK));
        String turn = dbGameDao.getTurn(1111);
        assertThat(turn).isEqualTo(Team.of(Team.BLACK));
    }

    @AfterEach
    void afterEach() {
        DbGameDao dbGameDao = new DbGameDao();
        dbGameDao.deleteGameData(1111);
    }
}
