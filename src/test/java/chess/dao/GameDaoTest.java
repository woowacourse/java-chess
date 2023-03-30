package chess.dao;

import chess.dao.GameDao;
import chess.dao.entity.GameEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class GameDaoTest {

    private final GameDao gameDao = new GameDao();

    @Test
    public void connection() throws SQLException {
        try (final var connection = gameDao.getConnection()) {
            Assertions.assertThat(connection).isNotNull();
        }
    }

    @Test
    public void addUser_test() {
        final var gameEntity = new GameEntity(1, "준비");
        gameDao.addTurn(gameEntity);
    }

    @Test
    public void updateByTurn_test() {
        gameDao.updateByTurn(1,"진행중");
    }

    @Test
    public void findByGameId_test() {
        Assertions.assertThat(gameDao.findByGameId(1)).isEqualTo("진행중");
        gameDao.findByGameId(1);
    }
}
