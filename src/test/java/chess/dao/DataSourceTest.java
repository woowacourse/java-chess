package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DataSourceTest {

    @DisplayName("DB와 연결이 되는지 확인한다.")
    @Test
    void getConnection() {
        final String url = "jdbc:mysql://localhost:3306/chess";
        final String user = "user";
        final String password = "password";

        DataSource dataSource = new DataSourceImpl(url, user, password);
        assertThat(dataSource.connection()).isNotNull();
    }
}
