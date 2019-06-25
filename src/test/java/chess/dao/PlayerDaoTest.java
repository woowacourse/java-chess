package chess.dao;

import org.junit.jupiter.api.Test;

import java.sql.SQLDataException;

import static org.assertj.core.api.Java6Assertions.assertThat;

class PlayerDaoTest {
    @Test
    void insertPlayers() throws SQLDataException {
        assertThat(PlayerDao.getInstance().insertPlayers("김고래", "규동"))
                .isNotNull();
    }
}