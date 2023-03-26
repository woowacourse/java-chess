package chess.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.game.Game;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class GameDaoTest {
    private final GameDao gameDao = new GameDao();

    @Test
    void connection() throws SQLException {
        try (final var connection = gameDao.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }

    @Test
    void addMove() {
        final var game = new Game();
        gameDao.addMove(game);
    }
}
