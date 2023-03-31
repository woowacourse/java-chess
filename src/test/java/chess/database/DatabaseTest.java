package chess.database;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DatabaseTest {

    @Test
    void connection() {
        Database database = new Database(DatabaseName.TEST);
        assertThat(database.getConnection()).isNotNull();
    }
}
