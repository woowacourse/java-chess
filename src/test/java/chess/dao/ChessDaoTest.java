package chess.dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

class ChessDaoTest {

    @Test
    void getConnectionTest() {
        Connection connection = new ChessDao().getConnection();

        assertThat(connection).isInstanceOf(Connection.class);
    }
}