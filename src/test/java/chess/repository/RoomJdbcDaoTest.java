package chess.repository;

import static org.assertj.core.api.Assertions.assertThat;

import chess.db.JdbcTemplate;
import chess.db.TestConnectionPool;
import chess.dto.room.RoomDto;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class RoomJdbcDaoTest {
    private static TestConnectionPool connectionPool;
    private static JdbcTemplate jdbcTemplate;
    private static RoomDao roomDao;

    @BeforeAll
    static void beforeAll() {
        connectionPool = new TestConnectionPool();
        jdbcTemplate = new JdbcTemplate(connectionPool);
        roomDao = new RoomJdbcDao(jdbcTemplate);
        final String query = "CREATE TABLE IF NOT EXISTS room ("
                + "  id int PRIMARY KEY AUTO_INCREMENT,"
                + "  name varchar(255),"
                + "  finished boolean,"
                + "  user_id int"
                + ");";
        jdbcTemplate.executeUpdate(query);
    }

    @BeforeEach
    void setUp() {
        roomDao.deleteAll();
    }

    @AfterAll
    static void afterAll() {
        connectionPool.closeConnection();
    }

    @Test
    void 사용자의_아이디와_방_이름을_입력받아_저장한다() {
        // given
        final String roomName = "체스 한 판 하시죠?";
        final int userId = 1;

        // when
        roomDao.save(roomName, userId);

        // then
        final List<RoomDto> result = roomDao.findAllByUserId(userId);
        assertThat(result).hasSize(1);
    }

    @Test
    void 사용자의_아이디를_받아_참가한_방_목록을_조회한다() {
        // given
        final int userId = 1;
        roomDao.save("방1", userId);
        roomDao.save("방2", userId);

        // when
        final List<RoomDto> result = roomDao.findAllByUserId(userId);

        // then
        assertThat(result).hasSize(2);
    }

    @Test
    void 방_아이디를_받아_방을_조회한다() {
        // given
        final int userId = 1;
        roomDao.save("방1", userId);
        final List<RoomDto> rooms = roomDao.findAllByUserId(userId);
        final int roomId = rooms.get(0).getId();

        // when
        final RoomDto room = roomDao.findById(roomId);

        // then
        assertThat(room.getName()).isEqualTo("방1");
    }
}
