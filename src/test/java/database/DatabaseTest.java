package database;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DatabaseTest {

    private final Database database = new Database();

    @Test
    @DisplayName("데이터베이스를 연결한다.")
    public void connection() {
        try (final var connection = database.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
