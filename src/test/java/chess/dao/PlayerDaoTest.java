package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.game.state.Player;
import chess.domain.piece.property.Color;
import java.sql.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerDaoTest {

    @BeforeEach
    void init() {
        final PlayerDao playerDao = new PlayerDao();
        playerDao.deleteAll();
    }

    @Test
    void connection() {
        final PlayerDao playerDao = new PlayerDao();
        final Connection connection = playerDao.getConection();
        assertThat(connection).isNotNull();
    }

    @Test
    void save() {
        final PlayerDao playerDao = new PlayerDao();
        playerDao.save(Color.BLACK);
    }

    @Test
    void findAll() {
        final PlayerDao playerDao = new PlayerDao();
        playerDao.save(Color.BLACK);
        final Player player = playerDao.findAll();

        assertThat(player).isEqualTo(Player.of(Color.BLACK));
    }

    @Test
    void deleteAll() {
        final PlayerDao playerDao = new PlayerDao();
        playerDao.deleteAll();
        final Player player = playerDao.findAll();

        assertThat(player).isNull();
    }
}
