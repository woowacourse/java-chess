package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.util.JdbcTemplate;
import chess.util.JdbcTestFixture;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnDaoTest {

    private TurnDao turnDao;

    @BeforeEach
    void setUp() {
        Connection connection = JdbcTemplate.getConnection(JdbcTestFixture.DEV_URL);
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        turnDao = new TurnDaoImpl(connection);
    }

    @Test
    @DisplayName("현재 턴을 가져온다.")
    void getTurn() {
        assertThat(turnDao.getCurrentTurn()).isEqualTo("white");
    }

    @Test
    @DisplayName("턴을 변경하고 가져온다.")
    void updateAndGetCurrentTurn() {
        turnDao.updateTurn("black", "white");
        assertThat(turnDao.getCurrentTurn()).isEqualTo("black");
    }

    @AfterEach
    void teardown() {
        try (Connection connection = JdbcTemplate.getConnection(JdbcTestFixture.DEV_URL)) {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
