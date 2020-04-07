package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessDBTest {

    @DisplayName("Connection Test")
    @Test
    public void connection() {
        Connection con = ChessDB.getConnection();
        assertThat(Objects.nonNull(con)).isTrue();
    }

    @Test
    void closeConnection() {
    }
}