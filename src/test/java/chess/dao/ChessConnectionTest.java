package chess.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChessConnectionTest {

    @DisplayName("DB 잘못된 서버로 연결시 throws Exception")
    @Test
    void connectionWrongServerThrowsException() {
        ChessConnection chessConnection = new ChessConnection(new ConnectionProperty("localhost:13307", "db_name",
                "?useSSL=false&serverTimezone=UTC", "root", "root"));

        assertThatThrownBy(() -> chessConnection.getConnection())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Communications link failure");
    }

    @DisplayName("DB 잘못된 DB로 연결시 throws Exception")
    @Test
    void connectionWrongDBThrowsException() {
        ChessConnection chessConnection = new ChessConnection(new ConnectionProperty("localhost:13306", "db_nam",
                "?useSSL=false&serverTimezone=UTC", "root", "root"));

        assertThatThrownBy(() -> chessConnection.getConnection())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unknown database");
    }

    @DisplayName("DB 잘못된 option timezone으로 연결시 throws Exception")
    @Test
    void connectionWrongOptionTimezoneThrowsException() {
        ChessConnection chessConnection = new ChessConnection(new ConnectionProperty("localhost:13306", "db_name",
                "?useSSL=false&serverTimezone=UT", "root", "root"));

        assertThatThrownBy(() -> chessConnection.getConnection())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("timezone");
    }

    @DisplayName("DB 잘못된 username으로 연결시 throws Exception")
    @Test
    void connectionWrongUserNameThrowsException() {
        ChessConnection chessConnection = new ChessConnection(new ConnectionProperty("localhost:13306", "db_name",
                "?useSSL=false&serverTimezone=UTC", "roo", "root"));

        assertThatThrownBy(() -> chessConnection.getConnection())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Access denied for user");
    }

    @DisplayName("DB 잘못된 password으로 연결시 throws Exception")
    @Test
    void connectionWrongPasswordThrowsException() {
        ChessConnection chessConnection = new ChessConnection(new ConnectionProperty("localhost:13306", "db_name",
                "?useSSL=false&serverTimezone=UTC", "root", "roo"));

        assertThatThrownBy(() -> chessConnection.getConnection())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Access denied for user");
    }

    @DisplayName("DB 연결 확인")
    @Test
    void getConnection() throws SQLException {
        ChessConnection chessConnection = new ChessConnection(new ConnectionProperty());
        Connection connection = chessConnection.getConnection();

        assertThat(connection.isClosed()).isFalse();
    }

    @DisplayName("DB 닫힘 확인")
    @Test
    void closeConnection() throws SQLException {
        ChessConnection chessConnection = new ChessConnection(new ConnectionProperty());
        Connection connection = chessConnection.getConnection();

        chessConnection.closeConnection(connection);

        assertThat(connection.isClosed()).isTrue();
    }

    @Test
    public void connection() {
        ChessConnection chessConnection = new ChessConnection(new ConnectionProperty());
        Connection connection = chessConnection.getConnection();

        assertNotNull(connection);
    }
}
