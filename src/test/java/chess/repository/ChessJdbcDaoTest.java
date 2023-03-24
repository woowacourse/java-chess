package chess.repository;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dto.MoveDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    private static ChessDao chessDao;
    private static Connection connection;

    @BeforeAll
    static void beforeAll() {
        connection = TestConnection.getConnection();
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(connection);
        chessDao = new ChessJdbcDao(jdbcTemplate);
        final String testTableGenerateQuery = "CREATE TABLE IF NOT EXISTS move (\n"
                + "    id     INT        NOT NULL AUTO_INCREMENT PRIMARY KEY,\n"
                + "    source VARCHAR(2) NOT NULL,\n"
                + "    target VARCHAR(2) NOT NULL\n"
                + ");";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(testTableGenerateQuery)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DatabaseConnectionFailException();
        }
    }

    @BeforeEach
    void setUp() {
        chessDao.deleteAll();
    }

    @AfterAll
    static void afterAll() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseConnectionFailException();
        }
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
