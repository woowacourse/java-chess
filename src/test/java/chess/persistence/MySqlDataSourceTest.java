package chess.persistence;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MySqlDataSourceTest {
    @Test
    void create() {
        assertDoesNotThrow(() -> new MySqlDataSource().getConnection());
    }
}
