package chess.dao;

import org.junit.jupiter.api.Test;

class MySqlManagerTest {

    @Test
    void init_MySQL() throws Exception {
        MySqlManager.initMySQL();
    }
}
