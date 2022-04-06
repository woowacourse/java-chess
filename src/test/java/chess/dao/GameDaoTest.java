package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Team;
import chess.dto.GameDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameDaoTest {

    private final GameDao gameDao = new GameDao();
    private int gameId;

    @BeforeEach
    void init() {
        gameDao.save(new GameDto("a", "b", "WHITE"));
        gameId = gameDao.findGameIdByUserName("a", "b");
    }

    @AfterEach
    void clear() {
        gameDao.deleteById(gameId);
    }

    @Test
    void save() {
    }

    @Test
    void findGameIdByUserName() {
        int gameIdByUserName = gameDao.findGameIdByUserName("a", "b");

        assertThat(gameIdByUserName).isNotZero();
    }

    @Test
    void findById() {
        GameDto gameDto = gameDao.findById(gameId);

        assertThat(gameDto.getWhiteUserName()).isEqualTo("a");
    }

    @Test
    void update() {
        gameDao.update(Team.WHITE.name(), gameId);
        GameDto gameDto = gameDao.findById(gameId);

        assertThat(gameDto.getState()).isEqualTo(Team.BLACK.name());
    }
}
