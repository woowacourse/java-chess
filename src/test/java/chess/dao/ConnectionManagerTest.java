package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.SQLException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConnectionManagerTest {

    @DisplayName("DB 커넥션을 반환한다.")
    @Test
    void DB_커넥션_반환() {
        try (Connection connection = ConnectionManager.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException exception) {
            Assertions.fail(exception.getMessage());
        }
    }
}
