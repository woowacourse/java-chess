package repository;

import connection.ChessConnectionGenerator;
import domain.player.Player;
import domain.player.PlayerName;
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

    @DisplayName("사용자의 이름을 사용자를 생성한다.")
    @Test
    void create() {
        // given
        final String name = "pobi";
        final PlayerName playerName = new PlayerName(name);

        // when
        final Player player = playerDao.add(playerName);

        // then
        assertThat(player.getName()).isEqualTo(name);
    }
}
