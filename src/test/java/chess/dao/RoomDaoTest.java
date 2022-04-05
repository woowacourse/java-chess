package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.constants.TestConstants;
import chess.entity.Room;
import chess.utils.JdbcTemplate;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RoomDaoTest {

    private RoomDao roomDao;
    private Connection connection;

    @BeforeEach
    void beforeEach() {
        connection = JdbcTemplate.getConnection(TestConstants.TEST_DB_URL);
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        roomDao = new RoomDao(connection);
    }

    @AfterEach
    void afterEach() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("ID로 Room을 가져온다.")
    void findById() {
        Room room = roomDao.findById(1);
        assertThat(room.getTurn()).isEqualTo("white");
    }

    @Test
    @DisplayName("Room을 새로 생성한다.")
    void save() {
        Room room = new Room();
        assertThat(roomDao.save(room)).isTrue();
    }
}
