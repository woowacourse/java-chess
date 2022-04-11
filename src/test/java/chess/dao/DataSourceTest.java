package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DataSourceTest {

    @DisplayName("DB와 연결이 되는지 확인한다.")
    @Test
    void getConnection() {
        DataSource dataSource = new DataSourceImpl();
        assertThat(dataSource.connection()).isNotNull();
    }
}
