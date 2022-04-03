package chess.db.dao;

import static chess.util.DatabaseUtil.getConnection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.db.entity.GameEntity;
import chess.domain.game.GameState;
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

    private final GameDao dao = new GameDao(TEST_TABLE_NAME);

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
    void findById_메서드로_id에_해당되는_데이터_조회가능() {
        GameEntity actual = dao.findById(1);
        GameEntity expected = new GameEntity(1, GameState.WHITE_TURN);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findById_메서드에서_id에_해당되는_데이터가_없는_경우_예외발생() {
        assertThatThrownBy(() -> dao.findById(99999))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void save_메서드로_신규_게임_데이터_저장가능() {
        GameEntity newGame = new GameEntity(10, GameState.WHITE_TURN);

        dao.save(newGame);
        GameEntity actual = dao.findById(10);

        assertThat(actual).isEqualTo(newGame);
    }

    @Test
    void save_메서드로_ID가_중복되는_게임_데이터_저장_시도시_예외발생() {
        GameEntity existingGame = new GameEntity(1, GameState.WHITE_TURN);
        assertThatThrownBy(() -> dao.save(existingGame))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void updateState_메서드로_게임의_상태_변경가능() {
        GameEntity updatedGame = new GameEntity(1, GameState.BLACK_TURN);

        dao.updateState(updatedGame);
        GameEntity actual = dao.findById(1);

        assertThat(actual).isEqualTo(updatedGame);
    }

    @Test
    void updateState_메서드로_저장되지_않은_게임_데이터_변경_시도시_예외발생() {
        GameEntity nonExistingGame = new GameEntity(99999, GameState.WHITE_TURN);
        assertThatThrownBy(() -> dao.updateState(nonExistingGame))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkById_메서드로_id에_해당되는_데이터_존재여부_확인가능() {
        boolean actual = dao.checkById(1);

        assertThat(actual).isTrue();
    }

    @Test
    void checkById_메서드에서_id에_해당되는_데이터가_없으면_거짓_반환() {
        boolean actual = dao.checkById(9999);

        assertThat(actual).isFalse();
    }

    @Test
    void countAll_메서드로_여태까지_저장된_모든_데이터의_개수_조회가능() {
        int actual = dao.countAll();

        assertThat(actual).isEqualTo(3);
    }

    @Test
    void countByState_메서드로_특정_state에_해당되는_데이터의_개수_조회가능(){
        int actual = dao.countByState(GameState.OVER);

        assertThat(actual).isEqualTo(1);
    }
}