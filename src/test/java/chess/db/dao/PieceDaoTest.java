package chess.db.dao;

import static chess.util.DatabaseUtil.getConnection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import chess.db.entity.PieceEntity;
import chess.domain.board.position.Position;
import chess.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class PieceDaoTest {

    private static final String TEST_TABLE_NAME = "piece_test";
    private static final String TEST_FIXTURE_INSERT_SQL = "INSERT INTO "
            + TEST_TABLE_NAME + " (game_id, position, type, color) VALUES ";
    private static final List<String> SETUP_TEST_DB_SQL = List.of(
            TEST_FIXTURE_INSERT_SQL + "(1, 'a1', 'PAWN', 'WHITE')",
            TEST_FIXTURE_INSERT_SQL + "(1, 'c3', 'KNIGHT', 'BLACK')",
            TEST_FIXTURE_INSERT_SQL + "(1, 'e3', 'QUEEN', 'BLACK')",
            TEST_FIXTURE_INSERT_SQL + "(10, 'a1', 'PAWN', 'WHITE')",
            TEST_FIXTURE_INSERT_SQL + "(10, 'c3', 'KNIGHT', 'BLACK')",
            TEST_FIXTURE_INSERT_SQL + "(10, 'e3', 'QUEEN', 'BLACK')");

    private static final String CLEANSE_TEST_DB_SQL = "TRUNCATE TABLE " + TEST_TABLE_NAME;

    private final PieceDao dao = new PieceDao(TEST_TABLE_NAME);

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
    void findAllByGameId_메서드로_gameId에_해당되는_모든_체스말_데이터_조회가능() {
        List<PieceEntity> actual = dao.findAllByGameId(1);
        List<PieceEntity> expected = List.of(
                new PieceEntity("a1", "PAWN", "WHITE"),
                new PieceEntity("c3", "KNIGHT", "BLACK"),
                new PieceEntity("e3", "QUEEN", "BLACK"));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findAllByGameId_메서드로_gameId에_해당되는_데이터가_없는_경우_빈_리스트_반환() {
        List<PieceEntity> actual = dao.findAllByGameId(99999);
        List<PieceEntity> expected = List.of();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void saveAll_메서드로_복수의_신규_체스말_데이터_저장가능() {
        dao.saveAll(2, List.of(
                new PieceEntity("a1", "PAWN", "WHITE"),
                new PieceEntity("c3", "QUEEN", "BLACK")));

        int createdDataCount = DatabaseUtil.getCountResult(
                "SELECT COUNT(*) FROM " + TEST_TABLE_NAME + " WHERE game_id = 2");

        assertThat(createdDataCount).isEqualTo(2);
    }

    @Test
    void deleteAllByGameIdAndPositions_메서드로_복수의_체스말_데이터_삭제가능() {
        dao.deleteAllByGameIdAndPositions(10,
                List.of(Position.of("a1"), Position.of("c3")));

        int dataLeftCount = DatabaseUtil.getCountResult(
                "SELECT COUNT(*) FROM " + TEST_TABLE_NAME + " WHERE game_id = 10");

        assertThat(dataLeftCount).isEqualTo(3 - 2);
    }

    @Test
    void deleteAllByGameIdAndPositions_메서드로_존재하지_않는_체스말_데이터_삭제시도시_예외_미발생() {
        assertThatNoException().isThrownBy(() -> dao.deleteAllByGameIdAndPositions(
                1, List.of(Position.of("f3"), Position.of("f8")))
        );
    }
}
