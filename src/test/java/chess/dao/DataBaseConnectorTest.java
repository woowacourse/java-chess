package chess.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

class DataBaseConnectorTest {

    @Test
    @DisplayName("DB 접속에 성공하면 null이 나오지 않는다.")
    void connection() {
        DataBaseConnector dataBaseConnector = new DataBaseConnector("localhost:13306",
                "db_chess",
                "?useSSL=false&serverTimezone=UTC&characterEncoding=utf8",
                "root",
                "root");
        Connection connection = dataBaseConnector.getConnection();
        assertThat(connection).isNotNull();
    }
}