package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

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
        final Connection connection = Connector.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void save() {
        assertThatCode(() -> {
            gameDao.save("blackrunning");
        }).doesNotThrowAnyException();

        gameDao.delete(gameDao.findGameId());
    }

    @Test
    void findAGameId() {
        gameDao.save("whiterunning");

        assertThat(gameDao.findGameId()).isInstanceOf(Integer.class);

        gameDao.delete(gameDao.findGameId());
    }

    @Test
    void findAGameState() {
        gameDao.save("whiterunning");
        String state = gameDao.findGameState();

        assertThat(state).isEqualTo("whiterunning");

        gameDao.delete(gameDao.findGameId());
    }

    @Test
    void update() {
        assertThatCode(() -> {
            gameDao.save("blackrunning");
            gameDao.update("blackrunning", gameDao.findGameId());
        }).doesNotThrowAnyException();

        gameDao.delete(gameDao.findGameId());
    }

    @Test
    void delete() {
        gameDao.save("blackrunning");

        assertThatCode(() -> {
            gameDao.delete(gameDao.findGameId());
        }).doesNotThrowAnyException();
    }
}
