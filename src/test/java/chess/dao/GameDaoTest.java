package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.dto.GameDto;
import java.sql.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameDaoTest {

    private GameDao gameDao;

    @BeforeEach
    void setUp() {
        gameDao = new GameDao();
    }

    @Test
    void connection() {
        final Connection connection = gameDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void save() {
        assertThatCode(() -> {
            gameDao.save(new GameDto("blackrunning"));
        }).doesNotThrowAnyException();

        gameDao.delete(gameDao.findGameId());
    }

    @Test
    void findAGameId() {
        gameDao.save(new GameDto("whiterunning"));

        assertThat(gameDao.findGameId()).isInstanceOf(Integer.class);

        gameDao.delete(gameDao.findGameId());
    }

    @Test
    void findAGameState() {
        gameDao.save(new GameDto("whiterunning"));
        GameDto gameState = gameDao.findGameState();
        String state = gameState.getState();

        assertThat(state).isEqualTo("whiterunning");

        gameDao.delete(gameDao.findGameId());
    }

    @Test
    void update() {
        assertThatCode(() -> {
            gameDao.save(new GameDto("blackrunning"));
            gameDao.update(new GameDto("blackrunning"), gameDao.findGameId());
        }).doesNotThrowAnyException();

        gameDao.delete(gameDao.findGameId());
    }

    @Test
    void delete() {
        gameDao.save(new GameDto("blackrunning"));

        assertThatCode(() -> {
            gameDao.delete(gameDao.findGameId());
        }).doesNotThrowAnyException();
    }
}
