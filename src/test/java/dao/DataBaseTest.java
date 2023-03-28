package dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class DataBaseTest {

    private final DataBase dataBase = new DataBase();

    @Test
    public void connection() {
        try (final var connection = dataBase.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
