package chess.db;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.assertj.core.api.Assertions.assertThatNoException;

class DBConnectionTest {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION;

    @Test
    @DisplayName("Mysql 연결 테스트")
    public void testConnection() {
        assertThatNoException().isThrownBy(DBConnection::getConnection);
    }
}