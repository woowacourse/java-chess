package repository;

import connection.ChessConnectionGenerator;
import domain.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerDaoTest {

    final Connection connection = ChessConnectionGenerator.getTestConnection();
    final PlayerDao playerDao = new PlayerDao(connection);

    @BeforeEach
    void before() {
        try {
            if (connection != null) {
                connection.setAutoCommit(false);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void after() {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("사용자의 이름을 저장한다.")
    @Test
    void create() {
        // given
        final String name = "pobi";
        final Player player = new Player(name);

        // when
        final int playerId = playerDao.add(player);

        // then
        assertThat(playerDao.findNameById(playerId).get()).isEqualTo(name);
    }
}
