package chess.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChessConnectionTest {

    @DisplayName("DB 연결 확인")
    @Test
    void getConnection() throws SQLException {
        ChessConnection chessConnection = ChessConnection.getInstance();
        Connection connection = chessConnection.getConnection();

        assertThat(connection.isClosed()).isFalse();
    }

    @DisplayName("DB 닫힘 확인")
    @Test
    void closeConnection() throws SQLException {
        ChessConnection chessConnection = ChessConnection.getInstance();
        Connection connection = chessConnection.getConnection();

        chessConnection.freeConnection(connection);

        assertThat(connection.isClosed()).isTrue();
    }

    @Test
    public void connection() {
        ChessConnection chessConnection = ChessConnection.getInstance();
        Connection connection = chessConnection.getConnection();

        assertNotNull(connection);
    }
}
