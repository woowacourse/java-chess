package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JDBCConnectionTest {

    final DBConnection DBConnection = new JDBCConnection();

    @DisplayName("연결")
    @Test
    void connect() {
        assertThatNoException().isThrownBy(DBConnection::getConnection);
    }

    @DisplayName("닫기")
    @Test
    void close() throws SQLException {
        Connection connection = DBConnection.getConnection();

        DBConnection.close();

        assertThat(connection.isClosed()).isTrue();
    }
}