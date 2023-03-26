package chess.repository;

import static org.assertj.core.api.Assertions.assertThat;

import chess.db.JdbcTemplate;
import chess.db.TestConnectionPool;
import chess.dto.MoveDto;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ChessJdbcDaoTest {
    private static TestConnectionPool connectionPool;
    private static JdbcTemplate jdbcTemplate;
    private static GameDao gameDao;

    @BeforeAll
    static void beforeAll() {
        connectionPool = new TestConnectionPool();
        jdbcTemplate = new JdbcTemplate(connectionPool);
        gameDao = new GameJdbcDao(jdbcTemplate);
        final String query = "CREATE TABLE IF NOT EXISTS move ("
                + "    id     INT        NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                + "    source VARCHAR(2) NOT NULL,"
                + "    target VARCHAR(2) NOT NULL,"
                + "    room_id INT NOT NULL"
                + ");";
        jdbcTemplate.executeUpdate(query);
    }

    @BeforeEach
    void setUp() {
        gameDao.deleteAll();
    }

    @AfterAll
    static void afterAll() {
        connectionPool.closeConnection();
    }

    @Test
    void 기물의_이동과_방_아이디를_받아_기물의_움직임을_저장한다() {
        // given
        final MoveDto moveDto = new MoveDto("d2", "d4");
        final int roomId = 1;

        // when
        gameDao.save(moveDto, roomId);

        // then
        final List<MoveDto> result = gameDao.findAllByRoomId(roomId);
        assertThat(result.size()).isOne();
    }

    @Test
    void 방_아이디를_입력받아_모든_기보를_조회한다() {
        // given
        final int roomId = 1;
        gameDao.save(new MoveDto("d2", "d4"), roomId);
        gameDao.save(new MoveDto("d7", "d5"), roomId);

        // when
        final List<MoveDto> result = gameDao.findAllByRoomId(roomId);

        // then
        assertThat(result.size()).isEqualTo(2);
    }
}
