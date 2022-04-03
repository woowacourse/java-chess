package chess.db.dao;

import static chess.util.DatabaseUtil.getConnection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.GameResult;
import chess.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class ResultDaoTest {

    private static final String TEST_TABLE_NAME = "result_test";
    private static final String SETUP_TEST_DB_SQL = "INSERT INTO " + TEST_TABLE_NAME
            + " (game_id, winner, white_score, black_score) VALUES (1, 'WHITE', 37, 10.5)";

    private static final String CLEANSE_TEST_DB_SQL = "TRUNCATE TABLE " + TEST_TABLE_NAME;

    private final ResultDao dao = new ResultDao(TEST_TABLE_NAME);

    @BeforeAll
    static void setUp() {
        cleanUp();
        try (final Connection connection = getConnection()) {
            connection.prepareStatement(SETUP_TEST_DB_SQL)
                    .executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void cleanUp() {
        try (final Connection connection = getConnection()) {
            connection.prepareStatement(CLEANSE_TEST_DB_SQL)
                    .executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findByGameId_메서드로_gameId에_해당되는_게임_결과_조회가능() {
        GameResult actual =  dao.findByGameId(1);
        GameResult expected =  new GameResult("WHITE", 37, 10.5);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findByGameId_메서드로_gameId에_해당되는_데이터가_없는_경우_예외발생() {
        assertThatThrownBy(() -> dao.findByGameId(99999))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void save_메서드로_복수의_신규_게임_결과_데이터_저장가능() {
        dao.save(2, new GameResult("WHITE", 37, 10.5));

        int createdDataCount = DatabaseUtil.getCountResult(
                "SELECT COUNT(*) FROM " + TEST_TABLE_NAME + " WHERE game_id = 2");

        assertThat(createdDataCount).isEqualTo(1);
    }
}