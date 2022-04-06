package chess.dao;

import chess.domain.Team;
import chess.dto.GameInformationDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DbGameDaoTest {

    @Test
    void saveGameTest() {
        DbGameDao dbGameDao = new DbGameDao();
        dbGameDao.saveGame(GameInformationDto.of(1111, Team.WHITE));
    }

    @Test
    void getGameDataTest() {
        DbGameDao dbGameDao = new DbGameDao();
        dbGameDao.saveGame(GameInformationDto.of(1111, Team.WHITE));
        GameInformationDto gameInformationDto = dbGameDao.getGameData(1111);
        assertThat(gameInformationDto.getId() == 1111 && gameInformationDto.getTurn().isSame(Team.WHITE)).isTrue();
    }

    @Test
    void deleteGameData() {
        DbGameDao dbGameDao = new DbGameDao();
        dbGameDao.saveGame(GameInformationDto.of(1112, Team.WHITE));
        dbGameDao.deleteGameData(1112);
    }

    @AfterEach
    void afterEach() {
        DbGameDao dbGameDao = new DbGameDao();
        dbGameDao.deleteGameData(1111);
    }
}
