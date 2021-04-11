package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.team.Team;
import chess.entity.Game;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class GameDaoTest {

    private GameDao gameDao;

    @BeforeEach
    void setup() {
        gameDao = new GameDao();
    }

    @Test
    void CRUD() {
        // create
        final Game game = new Game(0L, 1L, 2L, Team.WHITE, false, LocalDateTime.now());
        final long id = gameDao.insert(game);

        // read
        final Optional<Game> optionalGame = gameDao.selectById(id);
        assertThat(optionalGame).isPresent();
        final Game readGame = optionalGame.get();
        assertThat(id).isEqualTo(readGame.getId());
    }
}
