package dao;

import static org.assertj.core.api.Assertions.assertThat;

import domain.game.ChessBoardGenerator;
import domain.game.Game;
import domain.game.Side;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class GameDaoTest {
    private final GameDao gameDao = new GameDao();

    @Test
    public void connection() throws SQLException {
        try (final var connection = gameDao.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }

    @Test
    public void save() {
        Game game = new Game(new ChessBoardGenerator().generate(), Side.WHITE);
        gameDao.save(game);
    }

    @Test
    public void deleteById() {
        for (int i = 1; i < 65; i++) {
            gameDao.deleteById(i);
        }
    }
}
