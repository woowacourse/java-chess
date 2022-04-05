package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.Test;

class StateDaoTest {

    @Test
    void connection() {
        final StateDao stateDao = new StateDao();
        final Connection connection = stateDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void save() {
        final StateDao stateDao = new StateDao();
        stateDao.save("ready");
    }

    @Test
    void findState() {
        final StateDao stateDao = new StateDao();
        final String command = stateDao.findState();
        assertThat(command).isEqualTo("ready");
    }
}
