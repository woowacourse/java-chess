package chess.db.dao;

import static chess.util.DatabaseUtil.getConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class GameDaoTest {

    private static final String TEST_TABLE_NAME = "game_test";
    private static final List<String> SETUP_TEST_DB_SQL = List.of(
            "INSERT INTO " + TEST_TABLE_NAME + " (id, state) VALUES (1, 'WHITE_TURN')",
            "INSERT INTO " + TEST_TABLE_NAME + " (id, state) VALUES (2, 'BLACK_TURN')",
            "INSERT INTO " + TEST_TABLE_NAME + " (id, state) VALUES (3, 'OVER')");

    private static final String CLEANSE_TEST_DB_SQL = "TRUNCATE TABLE " + TEST_TABLE_NAME;

    @BeforeAll
    static void setUp() {
        cleanUp();
        try (final Connection connection = getConnection()) {
            for (String sql : SETUP_TEST_DB_SQL) {
                connection.prepareStatement(sql)
                        .executeUpdate();
            }
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
    void TBA() {
    }
}