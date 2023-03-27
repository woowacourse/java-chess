package chess.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import repository.connector.JdbcConnector;
import repository.room.JdbcRoomDao;
import utils.StringBytesCalculator;

class JdbcRoomDaoTest {
    JdbcConnector connector = new TestConnector();
    JdbcRoomDao jdbcChessDao = new JdbcRoomDao(connector);

    @BeforeEach
    void setUp() {
        jdbcChessDao.deleteAllMoveHistory();
        jdbcChessDao.deleteAllBoard();
        jdbcChessDao.deleteAllGame();
    }

    @Test
    @DisplayName("DB 커넥션 테스트")
    void connection() throws SQLException {
        try (Connection connection = connector.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }

    @Test
    @DisplayName("addGame을 통해 DB에 게임을 저장한다.")
    void addGame() {
        jdbcChessDao.createRoom("테스트");

        List<String> allGame = jdbcChessDao.findAllRooms();

        assertThat(allGame).contains("테스트");
    }

    @Test
    @DisplayName("addGame을 통해 DB에 게임을 저장한다.")
    void addGameFail() {
        String roomName = "가가가가가가";
        int bytesLength = StringBytesCalculator.calculateBytesLength(roomName);
        System.out.println(bytesLength);
        jdbcChessDao.createRoom(roomName);

        List<String> allGame = jdbcChessDao.findAllRooms();

        assertThat(allGame).contains(roomName);
    }
}
