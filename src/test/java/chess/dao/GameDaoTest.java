package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.team.Team;
import chess.entity.Game;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Optional<Game> optionalGame = gameDao.selectById(id);
        assertThat(optionalGame).isPresent();
        Game readGame = optionalGame.get();
        assertThat(id).isEqualTo(readGame.getId());

        // update
        final Game expectedGame = new Game(id, 1L, 2L, Team.BLACK, true, LocalDateTime.now());
        gameDao.update(expectedGame);
        optionalGame = gameDao.selectById(id);
        readGame = optionalGame.get();
        assertThat(expectedGame.getTurnValue()).isEqualTo(readGame.getTurnValue());
        assertThat(expectedGame.getFinished()).isEqualTo(readGame.getFinished());

        // delete
        gameDao.deleteById(id);
        assertThat(gameDao.selectById(id)).isEmpty();
    }
}
