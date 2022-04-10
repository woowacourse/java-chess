package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class GameDaoTest {

    private static final String TEST_TABLE = "game_test";
    private static final String SETUP_TEST_DB_SQL = String.format(
            "INSERT INTO %s (id, state) VALUES (1, 'RUNNING'), (2, 'OVER')", TEST_TABLE);

    private static final String CLEANSE_TEST_DB_SQL = String.format("TRUNCATE TABLE %s", TEST_TABLE);

    private final GameDao dao = new GameDao() {
        @Override
        protected String addTable(String sql) {
            return String.format(sql, TEST_TABLE);
        }
    };

    @BeforeEach
    void setUp() {
        cleanUp();
        new StatementExecutor(SETUP_TEST_DB_SQL).executeCommand();
    }

    @AfterEach
    void cleanUp() {
        new StatementExecutor(CLEANSE_TEST_DB_SQL).executeCommand();
    }

    @Test
    void saveAndGetGeneratedId_메서드는_저장한_데이터의_id값_반환() {
        int actual = dao.saveAndGetGeneratedId();

        assertThat(actual).isEqualTo(3);
    }

    @Test
    void finishGame_메서드로_게임의_상태를_OVER로_변경가능() {
        dao.finishGame(1);

        ResultReader reader = new StatementExecutor(String.format("SELECT state FROM %s WHERE id = %d", TEST_TABLE, 1))
                .executeQuery();
        String actual = reader.nextRow()
                .readStringAt("state");

        assertThat(actual).isEqualTo("OVER");
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

        assertThat(actual).isEqualTo(2);
    }

    @Test
    void countByState_메서드로_특정_state에_해당되는_데이터의_개수_조회가능() {
        int actual = dao.countByState(GameState.OVER);

        assertThat(actual).isEqualTo(1);
    }
}
