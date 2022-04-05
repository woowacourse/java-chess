package chess.web.dao;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.game.state.Player;

class PlayerDaoTest {
    @Test
    void save() {
        final PlayerDao playerDao = new PlayerDao();
        assertThatCode(() -> {
            int savedId = playerDao.save(Player.Black);
            remove(savedId);
        }).doesNotThrowAnyException();
    }

    @Test
    void find() {
        final PlayerDao playerDao = new PlayerDao();
        int savedId = playerDao.save(Player.Black);
        Player player = playerDao.find();
        assertThat(player).isEqualTo(Player.Black);
        remove(savedId);
    }

    private void remove(int id) {
        final PlayerDao playerDao = new PlayerDao();
        playerDao.remove(id);
    }
}