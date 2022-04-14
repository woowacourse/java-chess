package chess.db.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DBConnectorTest {

    @Test
    @DisplayName("연결 확인")
    void connection() {
        DBConnector dbConnector = new DBConnector();
        Connection connection = dbConnector.getConnection();

        assertThat(connection).isNotNull();
    }
}
