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
    private static ChessDao chessDao;

    @BeforeAll
    static void beforeAll() {
        connectionPool = new TestConnectionPool();
        jdbcTemplate = new JdbcTemplate(connectionPool);
        chessDao = new ChessJdbcDao(jdbcTemplate);
        final String query = "CREATE TABLE IF NOT EXISTS move ("
                + "    id     INT        NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                + "    source VARCHAR(2) NOT NULL,"
                + "    target VARCHAR(2) NOT NULL"
                + ");";
        jdbcTemplate.executeUpdate(query);
    }

    @BeforeEach
    void setUp() {
        chessDao.deleteAll();
    }

    @AfterAll
    static void afterAll() {
        connectionPool.closeConnection();
    }

    @Test
    void 시작위치와_도착위치를_받아_기물의_움직임을_저장한다() {
        // given
        final MoveDto moveDto = new MoveDto("d2", "d4");

        // when
        chessDao.save(moveDto);

        // then
        final List<MoveDto> result = chessDao.findAll();
        assertThat(result.size()).isOne();
    }

    @Test
    void 모든_기보를_제거한다() {
        // when
        chessDao.deleteAll();

        // then
        final List<MoveDto> result = chessDao.findAll();
        assertThat(result.size()).isZero();
    }

    @Test
    void 모든_기보를_조회한다() {
        // given
        chessDao.save(new MoveDto("d2", "d4"));
        chessDao.save(new MoveDto("d7", "d5"));

        // when
        final List<MoveDto> result = chessDao.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
    }
}
